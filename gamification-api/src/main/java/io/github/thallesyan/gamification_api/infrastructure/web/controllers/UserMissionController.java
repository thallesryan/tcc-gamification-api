package io.github.thallesyan.gamification_api.infrastructure.web.controllers;

import io.github.thallesyan.gamification_api.application.exceptions.UserMissionNotFound;
import io.github.thallesyan.gamification_api.application.usecases.UserMissionApplication;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.BindUserMissionRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.MissionBinding;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.ResolveGoalRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.StartMissionRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.MissionProgressResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.MissionResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.MissionStartResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.NextGoalResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.mappers.GoalMapper;
import io.github.thallesyan.gamification_api.infrastructure.web.mappers.UserMissionGoalMapper;
import io.github.thallesyan.gamification_api.infrastructure.web.mappers.UserMissionMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user-mission/")
@AllArgsConstructor
public class UserMissionController {

    private final UserMissionApplication userMissionApplication;
    private final UserMissionMapper userMissionMapper;
    private final UserMissionGoalMapper userMissionGoalMapper;

    @PostMapping("bind-missions")
    public ResponseEntity<MissionResponseDTO> bindMissionsToUser(@RequestBody BindUserMissionRequestDTO bindUserMissionRequestDTO) {
        var missions = bindUserMissionRequestDTO.getMissions();
        var missionsIds = missions.stream().map(MissionBinding::getIdentifier).map(UUID::fromString).collect(Collectors.toList());
        userMissionApplication.associateUserMission(bindUserMissionRequestDTO.getUserEmail(), missionsIds);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //todo get missions by user
    //header identifierType, identifierValue

    //todo start mission and set the fist goal in progress, return this goal
    //todo validate mission already started and return 422?
    //todo return 422 if the mission was not associated with the user... use bind-missions
    @PostMapping("start-mission")
    public ResponseEntity<MissionStartResponseDTO> start(@RequestBody StartMissionRequestDTO startMissionRequestDTO) {
        var userMission = switch (startMissionRequestDTO.getUserIdentification().getUserIdentifierType()){
            case EMAIL -> userMissionApplication.startMissionByUserEmail(startMissionRequestDTO.getUserIdentification().getUserIdentifierValue(), startMissionRequestDTO.getMissionIdentifier());
            case IDENTIFIER -> userMissionApplication.startMissionByUserIdentifier(startMissionRequestDTO.getUserIdentification().getUserIdentifierValue(), startMissionRequestDTO.getMissionIdentifier());
        };

        return new ResponseEntity<>(userMissionMapper.toStartMissionResponseDTO(userMission), HttpStatus.CREATED);
    }

    //todo resolve goal, modify return if all goals are finished
    //todo: return actual goal and next goal
    @GetMapping("mission/{missionId}/user/{userEmail}/next-goal")
    public ResponseEntity<NextGoalResponseDTO> getGoal(
            @PathVariable("missionId") String missionId,
            @PathVariable("userEmail") String userEmail,
            @RequestHeader("platform") String platform
    ) {
        try{
            var currentGoal = userMissionApplication
                    .getCurrentGoal(userEmail, platform, missionId)
                    .orElse(null);

            var nextGoal = userMissionApplication
                    .getNextGoal(userEmail, platform, missionId)
                    .orElse(null);;

            var lastGoal = userMissionApplication
                    .getLastGoal(userEmail, platform, missionId)
                    .orElse(null);;

            var nextGoalResponse = new NextGoalResponseDTO(userMissionGoalMapper.userGoalToUserGoalProgress(currentGoal), userMissionGoalMapper.userGoalToUserGoalProgress(nextGoal), userMissionGoalMapper.userGoalToUserGoalProgress(lastGoal));
            return ResponseEntity.ok(nextGoalResponse);
        }catch (UserMissionNotFound ex){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PostMapping("mission/{missionId}/user/{userEmail}/resolve-next-goal")
    public ResponseEntity<MissionStartResponseDTO> resolveGoal(
            @PathVariable("missionId") String missionId,
            @PathVariable("userEmail") String userEmail,
            @RequestHeader("platform") String platform
    ) {
        try{
            var userMission = userMissionApplication.resolveGoalInProgress(userEmail, platform, missionId);
            return new ResponseEntity<>(userMissionMapper.toStartMissionResponseDTO(userMission), HttpStatus.OK);
        }catch (UserMissionNotFound ex){
            return new  ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("{userEmail}/status/{missionStatus}")
    public ResponseEntity<List<MissionProgressResponseDTO>> bindMissionsToUser(
            @PathVariable("userEmail") String userEmail,
            @PathVariable("missionStatus") String missionStatus
    ) {
        var missions = userMissionApplication.getMissionsInProgressByUserIdentifier(userEmail, missionStatus);
        if(missions.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        var missionProgressResponse = missions.stream().map(userMissionMapper::toMissionProgressResponseDTO).toList();
        return new ResponseEntity<>(missionProgressResponse, HttpStatus.OK);
    }
}

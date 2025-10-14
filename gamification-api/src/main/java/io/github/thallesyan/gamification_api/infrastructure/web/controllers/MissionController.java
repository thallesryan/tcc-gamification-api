package io.github.thallesyan.gamification_api.infrastructure.web.controllers;

import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.MissionResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.MissionStartResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.mappers.MissionMapper;
import io.github.thallesyan.gamification_api.application.usecases.MissionApplication;
import io.github.thallesyan.gamification_api.application.usecases.UserMissionApplication;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.*;
import io.github.thallesyan.gamification_api.infrastructure.web.mappers.UserMissionMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

//todo criar bean validacoes
@RestController
@RequestMapping("/api/mission/")
@AllArgsConstructor
public class MissionController {

    private final MissionApplication missionApplication;
    private final UserMissionApplication userMissionApplication;
    private final MissionMapper missionMapper;
    private final UserMissionMapper userMissionMapper;

    @PostMapping("create")
    public ResponseEntity<MissionResponseDTO> createMission(@RequestBody MissionCreationRequestDTO missionCreationRequestDTO) {
        var createdMission = missionApplication.createMission(missionMapper.toMission(missionCreationRequestDTO));
        return new ResponseEntity<>(missionMapper.toMissionResponseDTO(createdMission), HttpStatus.CREATED);
    }

    @PostMapping("user/bind-missions")
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
    @PostMapping("user/start-mission")
    public ResponseEntity<MissionStartResponseDTO> start(@RequestBody StartMissionRequestDTO startMissionRequestDTO) {
        var userMission = switch (startMissionRequestDTO.getUserIdentification().getUserIdentifierType()){
            case EMAIL -> userMissionApplication.startMissionByUserEmail(startMissionRequestDTO.getUserIdentification().getUserIdentifierValue(), startMissionRequestDTO.getMissionIdentifier());
            case IDENTIFIER -> userMissionApplication.startMissionByUserIdentifier(startMissionRequestDTO.getUserIdentification().getUserIdentifierValue(), startMissionRequestDTO.getMissionIdentifier());
        };

        return new ResponseEntity<>(userMissionMapper.toStartMissionResponseDTO(userMission), HttpStatus.CREATED);
    }

    //todo resolve goal, modify return if all goals are finished
    @PostMapping("user/goal")
    public ResponseEntity<?> resolveGoal(@RequestBody BindUserMissionRequestDTO bindUserMissionRequestDTO) {
      return null;
    }

    //todo make endpoint to get mission progress and goals related

////////////////////////////////////////////////////////////////////

    //todo busca de rewards por missao do usuario
}

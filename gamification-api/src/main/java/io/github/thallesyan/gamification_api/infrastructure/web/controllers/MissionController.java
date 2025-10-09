package io.github.thallesyan.gamification_api.infrastructure.web.controllers;

import io.github.thallesyan.gamification_api.application.mappers.MissionMapper;
import io.github.thallesyan.gamification_api.application.usecases.MissionApplication;
import io.github.thallesyan.gamification_api.application.usecases.UserMissionApplication;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.BindUserMissionRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.MissionBinding;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.MissionCreationRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.MissionResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/mission/")
@AllArgsConstructor
public class MissionController {

    private final MissionApplication missionApplication;
    private final UserMissionApplication userMissionApplication;
    private final MissionMapper missionMapper;

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

}

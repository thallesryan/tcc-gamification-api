package io.github.thallesyan.gamification_api.infrastructure.web.controllers;

import io.github.thallesyan.gamification_api.application.mappers.MissionMapper;
import io.github.thallesyan.gamification_api.application.usecases.MissionApplication;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.MissionRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.MissionResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mission/")
@AllArgsConstructor
public class MissionController {

    private final MissionApplication missionApplication;
    private final MissionMapper missionMapper;

    @PostMapping("create")
    public ResponseEntity<MissionResponseDTO> createMission(@RequestBody MissionRequestDTO missionRequestDTO) {
        var createdMission = missionApplication.createMission(missionMapper.toMission(missionRequestDTO));
        return new ResponseEntity<>(missionMapper.toMissionResponseDTO(createdMission), HttpStatus.CREATED);
    }
}

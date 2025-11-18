package io.github.thallesyan.gamification_api.infrastructure.web.controllers;

import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.MissionResponseDTO;
import io.github.thallesyan.gamification_api.application.usecases.MissionApplication;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.*;
import io.github.thallesyan.gamification_api.infrastructure.web.mappers.MissionMapper;
import io.github.thallesyan.gamification_api.application.exceptions.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/mission/")
@AllArgsConstructor
public class MissionController {

    private final MissionApplication missionApplication;
    private final MissionMapper missionMapper;

    @PostMapping("create")
    public ResponseEntity<MissionResponseDTO> createMission(
            @RequestBody @Valid MissionCreationRequestDTO missionCreationRequestDTO,
            @RequestHeader("platform") String platform) {

        
        var createdMission = missionApplication.createMission(missionMapper.toMission(missionCreationRequestDTO, platform));
        return new ResponseEntity<>(missionMapper.toMissionResponseDTO(createdMission), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<MissionResponseDTO> findMissionById(@PathVariable UUID id) {
        var mission = missionApplication.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mission", id));
        
        return new ResponseEntity<>(missionMapper.toMissionResponseDTO(mission), HttpStatus.OK);
    }

    //todo make endpoint to get mission progress and goals related

////////////////////////////////////////////////////////////////////

    //todo busca de rewards por missao do usuario
}

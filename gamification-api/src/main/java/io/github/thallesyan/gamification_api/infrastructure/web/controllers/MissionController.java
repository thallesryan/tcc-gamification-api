package io.github.thallesyan.gamification_api.infrastructure.web.controllers;

import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.MissionResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.MissionStartResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.mappers.MissionMapper;
import io.github.thallesyan.gamification_api.application.usecases.MissionApplication;
import io.github.thallesyan.gamification_api.application.usecases.UserMissionApplication;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.*;
import io.github.thallesyan.gamification_api.infrastructure.web.mappers.UserMissionMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

//todo criar bean validacoes
@RestController
@RequestMapping("/api/mission/")
@AllArgsConstructor
public class MissionController {

    private final MissionApplication missionApplication;
    private final MissionMapper missionMapper;

    @PostMapping("create")
    public ResponseEntity<MissionResponseDTO> createMission(@RequestBody @Valid MissionCreationRequestDTO missionCreationRequestDTO) {
        var createdMission = missionApplication.createMission(missionMapper.toMission(missionCreationRequestDTO));
        return new ResponseEntity<>(missionMapper.toMissionResponseDTO(createdMission), HttpStatus.CREATED);
    }



    //todo make endpoint to get mission progress and goals related

////////////////////////////////////////////////////////////////////

    //todo busca de rewards por missao do usuario
}

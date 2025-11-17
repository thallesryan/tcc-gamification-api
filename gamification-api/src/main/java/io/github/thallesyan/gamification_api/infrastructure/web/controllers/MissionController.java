package io.github.thallesyan.gamification_api.infrastructure.web.controllers;

import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.MissionResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.MissionStartResponseDTO;
import io.github.thallesyan.gamification_api.application.usecases.BadgeApplication;
import io.github.thallesyan.gamification_api.application.usecases.MissionApplication;
import io.github.thallesyan.gamification_api.application.usecases.RarityApplication;
import io.github.thallesyan.gamification_api.application.usecases.RewardApplication;
import io.github.thallesyan.gamification_api.application.usecases.UserMissionApplication;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Reward;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Rule;
import io.github.thallesyan.gamification_api.domain.entities.reward.Badge;
import io.github.thallesyan.gamification_api.domain.entities.reward.Rarity;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.*;
import io.github.thallesyan.gamification_api.infrastructure.web.mappers.GoalMapper;
import io.github.thallesyan.gamification_api.infrastructure.web.mappers.MissionMapper;
import io.github.thallesyan.gamification_api.infrastructure.web.mappers.RuleMapper;
import io.github.thallesyan.gamification_api.infrastructure.web.mappers.RewardMapper;
import io.github.thallesyan.gamification_api.infrastructure.web.mappers.UserMissionMapper;
import io.github.thallesyan.gamification_api.infrastructure.exceptions.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

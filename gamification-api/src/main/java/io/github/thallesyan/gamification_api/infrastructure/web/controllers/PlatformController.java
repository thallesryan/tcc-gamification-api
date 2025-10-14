package io.github.thallesyan.gamification_api.infrastructure.web.controllers;

import io.github.thallesyan.gamification_api.application.usecases.PlatformApplication;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.MissionCreationRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.PlatformCreationRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.PlatformRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.MissionResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.PlatformCreationResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.mappers.PlatformMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/platform/")
@AllArgsConstructor
public class PlatformController {

    private final PlatformApplication platformApplication;
    private final PlatformMapper platformMapper;

    @PostMapping("create")
    public ResponseEntity<PlatformCreationResponseDTO> createMission(@RequestBody PlatformCreationRequestDTO platformCreationRequestDTO) {
        var platformCreated = platformApplication.createPlatform(platformMapper.toModel(platformCreationRequestDTO));
        return new ResponseEntity<>(platformMapper.toCreationResponseDTO(platformCreated), HttpStatus.CREATED);
    }
}

package io.github.thallesyan.gamification_api.infrastructure.web.controllers;

import io.github.thallesyan.gamification_api.application.usecases.PlatformApplication;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.MissionCreationRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.PlatformCreationRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.PlatformRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.MissionResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.PlatformCreationResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.mappers.PlatformMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/platform/")
@AllArgsConstructor
@Tag(name = "Platform", description = "Endpoints para gerenciamento de plataformas")
public class PlatformController {

    private final PlatformApplication platformApplication;
    private final PlatformMapper platformMapper;

    @Operation(summary = "Criar plataforma", description = "Cria uma nova plataforma no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Plataforma criada com sucesso",
                    content = @Content(schema = @Schema(implementation = PlatformCreationResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("create")
    public ResponseEntity<PlatformCreationResponseDTO> createPlatform(@RequestBody @Valid PlatformCreationRequestDTO platformCreationRequestDTO, @AuthenticationPrincipal Jwt principal) {
        var platformCreated = platformApplication.createPlatform(platformMapper.toModel(platformCreationRequestDTO));
        return new ResponseEntity<>(platformMapper.toCreationResponseDTO(platformCreated), HttpStatus.CREATED);
    }
}

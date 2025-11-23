package io.github.thallesyan.gamification_api.infrastructure.web.controllers;

import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.MissionResponseDTO;
import io.github.thallesyan.gamification_api.application.usecases.MissionApplication;
import io.github.thallesyan.gamification_api.infrastructure.security.PlatformValidationService;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.*;
import io.github.thallesyan.gamification_api.infrastructure.web.mappers.MissionMapper;
import io.github.thallesyan.gamification_api.application.exceptions.EntityNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/mission/")
@AllArgsConstructor
@Tag(name = "Mission", description = "Endpoints para gerenciamento de missões")
public class MissionController {

    private final MissionApplication missionApplication;
    private final MissionMapper missionMapper;
    private final PlatformValidationService platformValidationService;

    @Operation(summary = "Criar missão", description = "Cria uma nova missão no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missão criada com sucesso",
                    content = @Content(schema = @Schema(implementation = MissionResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("create")
    public ResponseEntity<MissionResponseDTO> createMission(
            @RequestBody @Valid MissionCreationRequestDTO missionCreationRequestDTO,
            @Parameter(description = "Nome da plataforma", required = true)
            @RequestHeader("platform") String platform) {

        platformValidationService.validatePlatformAccess(platform);
        
        var createdMission = missionApplication.createMission(missionMapper.toMission(missionCreationRequestDTO, platform));
        return new ResponseEntity<>(missionMapper.toMissionResponseDTO(createdMission), HttpStatus.CREATED);
    }

    @Operation(summary = "Buscar missão por ID", description = "Retorna os dados de uma missão pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão encontrada",
                    content = @Content(schema = @Schema(implementation = MissionResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada")
    })
    @GetMapping("{id}")
    public ResponseEntity<MissionResponseDTO> findMissionById(
            @Parameter(description = "ID da missão", required = true)
            @PathVariable UUID id) {
        var mission = missionApplication.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mission", id));
        
        return new ResponseEntity<>(missionMapper.toMissionResponseDTO(mission), HttpStatus.OK);
    }

    //todo make endpoint to get mission progress and goals related

////////////////////////////////////////////////////////////////////

    //todo busca de rewards por missao do usuario
}

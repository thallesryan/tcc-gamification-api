package io.github.thallesyan.gamification_api.infrastructure.web.controllers;

import io.github.thallesyan.gamification_api.application.usecases.GoalApplication;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.GoalUpdateRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.GoalResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.mappers.GoalMapper;
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
@RequestMapping("/api/goal/")
@AllArgsConstructor
@Tag(name = "Goal", description = "Endpoints para gerenciamento de objetivos")
public class GoalController {

    private final GoalApplication goalApplication;
    private final GoalMapper goalMapper;

    private UUID parseUUID(String id) {
        try {
            return UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("ID inválido: " + id);
        }
    }

    @Operation(summary = "Buscar objetivo por ID", description = "Retorna os dados de um objetivo pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Objetivo encontrado",
                    content = @Content(schema = @Schema(implementation = GoalResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Objetivo não encontrado")
    })
    @GetMapping("{id}")
    public ResponseEntity<GoalResponseDTO> findGoalById(
            @Parameter(description = "ID do objetivo", required = true)
            @PathVariable String id) {
        UUID goalId = parseUUID(id);
        var goal = goalApplication.findById(goalId)
                .orElseThrow(() -> new EntityNotFoundException("Goal", goalId));
        
        return new ResponseEntity<>(goalMapper.toGoalResponseDTO(goal), HttpStatus.OK);
    }

    @Operation(summary = "Atualizar objetivo", description = "Atualiza parcialmente um objetivo existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Objetivo atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = GoalResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Objetivo não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PatchMapping("{id}")
    public ResponseEntity<GoalResponseDTO> updateGoal(
            @Parameter(description = "ID do objetivo", required = true)
            @PathVariable String id,
            @RequestBody @Valid GoalUpdateRequestDTO goalUpdateRequestDTO) {

        UUID goalId = parseUUID(id);
        var goal = goalApplication.findById(goalId)
                .orElseThrow(() -> new EntityNotFoundException("Goal", goalId));

        var goalToUpdate = goalMapper.toGoal(goalUpdateRequestDTO);
        var updatedGoal = goalApplication.updateGoal(goalId, goalToUpdate);
        
        return new ResponseEntity<>(goalMapper.toGoalResponseDTO(updatedGoal), HttpStatus.OK);
    }
}


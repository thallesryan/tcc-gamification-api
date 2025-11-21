package io.github.thallesyan.gamification_api.infrastructure.web.controllers;

import io.github.thallesyan.gamification_api.application.usecases.RarityApplication;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.entities.reward.Rarity;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.RarityPointsRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.RarityResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.mappers.RarityMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rarity/")
@AllArgsConstructor
@Tag(name = "Rarity", description = "Endpoints para gerenciamento de raridades")
public class RarityController {

    private final RarityApplication rarityApplication;
    private final RarityMapper rarityMapper;

    @Operation(summary = "Listar todas as raridades", description = "Retorna todas as raridades de uma plataforma")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Raridades encontradas"),
            @ApiResponse(responseCode = "204", description = "Nenhuma raridade encontrada")
    })
    @GetMapping("all")
    public ResponseEntity<List<RarityResponseDTO>> getAllRarities(
            @Parameter(description = "Nome da plataforma", required = true)
            @RequestHeader("platform") String platform) {
        Platform platformEntity = new Platform(platform);
        List<RarityResponseDTO> rarities = rarityApplication.getAllRaritiesByPlatform(platformEntity).stream()
                .map(rarityMapper::toRarityResponseDTO)
                .collect(Collectors.toList());
        
        if (rarities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<>(rarities, HttpStatus.OK);
    }

    @Operation(summary = "Associar pontos às raridades", description = "Associa pontos às raridades de uma plataforma")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pontos associados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("points")
    public ResponseEntity<List<RarityResponseDTO>> associatePoints(
            @RequestBody List<RarityPointsRequestDTO> rarityPointsRequestDTOList,
            @Parameter(description = "Nome da plataforma", required = true)
            @RequestHeader("platform") String platform) {
        
        Platform platformEntity = new Platform(platform);

        List<Rarity> rarities = rarityPointsRequestDTOList.stream()
                .map(dto -> new Rarity(
                        dto.getValue(),
                        dto.getPoints(),
                        platformEntity
                ))
                .collect(Collectors.toList());

        rarityApplication.associatePointsList(rarities);

        List<RarityResponseDTO> responseRarities = rarityApplication.getAllRaritiesByPlatform(platformEntity).stream()
                .map(rarityMapper::toRarityResponseDTO)
                .collect(Collectors.toList());
        
        return new ResponseEntity<>(responseRarities, HttpStatus.OK);
    }
}
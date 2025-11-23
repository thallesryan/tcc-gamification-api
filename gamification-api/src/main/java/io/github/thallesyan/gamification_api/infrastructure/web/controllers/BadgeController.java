package io.github.thallesyan.gamification_api.infrastructure.web.controllers;

import io.github.thallesyan.gamification_api.application.usecases.BadgeApplication;
import io.github.thallesyan.gamification_api.application.usecases.RarityApplication;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.entities.reward.Badge;
import io.github.thallesyan.gamification_api.domain.entities.reward.Rarity;
import io.github.thallesyan.gamification_api.domain.entities.reward.RarityEnum;
import io.github.thallesyan.gamification_api.infrastructure.security.PlatformValidationService;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.BadgeCreationRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.BadgeResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.mappers.BadgeMapper;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/badge")
@AllArgsConstructor
@Tag(name = "Badge", description = "Endpoints para gerenciamento de badges")
public class BadgeController {

    private final BadgeApplication badgeApplication;
    private final BadgeMapper badgeMapper;
    private final PlatformValidationService platformValidationService;

    @Operation(summary = "Criar badge", description = "Cria um novo badge no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Badge criado com sucesso",
                    content = @Content(schema = @Schema(implementation = BadgeResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/create")
    public ResponseEntity<BadgeResponseDTO> createBadge(
            @RequestBody @Valid BadgeCreationRequestDTO badgeCreationRequestDTO,
            @Parameter(description = "Nome da plataforma", required = true)
            @RequestHeader("platform") String platform) {

        platformValidationService.validatePlatformAccess(platform);

        Badge badge = badgeMapper.toBadge(badgeCreationRequestDTO, platform);

        var createdBadge = badgeApplication.createBadge(badge);
        return new ResponseEntity<>(badgeMapper.toBadgeResponseDTO(createdBadge), HttpStatus.CREATED);
    }

    @Operation(summary = "Buscar badges por raridade", description = "Retorna todos os badges de uma determinada raridade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Badges encontrados"),
            @ApiResponse(responseCode = "204", description = "Nenhum badge encontrado")
    })
    @GetMapping()
    public ResponseEntity<List<BadgeResponseDTO>> getByRarity(
            @Parameter(description = "Raridade do badge", required = true)
            @RequestParam("rarity") RarityEnum rarityEnum,
            @Parameter(description = "Nome da plataforma", required = true)
            @RequestHeader("platform") String platform) {
        
        platformValidationService.validatePlatformAccess(platform);
        
        Platform platformEntity = new Platform(platform);
        var badges = badgeApplication.findBadgesByRarity(rarityEnum, platformEntity).stream()
                .map(badgeMapper::toBadgeResponseDTO)
                .collect(Collectors.toList());
        
        if (badges.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<>(badges, HttpStatus.OK);
    }
}

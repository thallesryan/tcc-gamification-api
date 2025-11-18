package io.github.thallesyan.gamification_api.infrastructure.web.controllers;

import io.github.thallesyan.gamification_api.application.usecases.BadgeApplication;
import io.github.thallesyan.gamification_api.application.usecases.RarityApplication;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.entities.reward.Badge;
import io.github.thallesyan.gamification_api.domain.entities.reward.Rarity;
import io.github.thallesyan.gamification_api.domain.entities.reward.RarityEnum;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.BadgeCreationRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.BadgeResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.mappers.BadgeMapper;
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
public class BadgeController {

    private final BadgeApplication badgeApplication;
    private final BadgeMapper badgeMapper;

    @PostMapping("/create")
    public ResponseEntity<BadgeResponseDTO> createBadge(
            @RequestBody @Valid BadgeCreationRequestDTO badgeCreationRequestDTO,
            @RequestHeader("platform") String platform) {

        Badge badge = badgeMapper.toBadge(badgeCreationRequestDTO, platform);

        var createdBadge = badgeApplication.createBadge(badge);
        return new ResponseEntity<>(badgeMapper.toBadgeResponseDTO(createdBadge), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<BadgeResponseDTO>> getByRarity(
            @RequestParam("rarity") RarityEnum rarityEnum,
            @RequestHeader("platform") String platform) {
        
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

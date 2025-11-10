package io.github.thallesyan.gamification_api.infrastructure.web.controllers;

import io.github.thallesyan.gamification_api.application.usecases.RarityApplication;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.entities.reward.Rarity;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.RarityPointsRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.RarityResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.mappers.RarityMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rarity/")
@AllArgsConstructor
public class RarityController {

    private final RarityApplication rarityApplication;
    private final RarityMapper rarityMapper;

    @GetMapping("all")
    public ResponseEntity<List<RarityResponseDTO>> getAllRarities(@RequestHeader("platform") String platform) {
        Platform platformEntity = new Platform(platform);
        List<RarityResponseDTO> rarities = rarityApplication.getAllRaritiesByPlatform(platformEntity).stream()
                .map(rarityMapper::toRarityResponseDTO)
                .collect(Collectors.toList());
        
        if (rarities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<>(rarities, HttpStatus.OK);
    }

    @PostMapping("points")
    public ResponseEntity<List<RarityResponseDTO>> associatePoints(
            @RequestBody List<RarityPointsRequestDTO> rarityPointsRequestDTOList, 
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
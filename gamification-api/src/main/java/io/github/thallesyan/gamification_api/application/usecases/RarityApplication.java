package io.github.thallesyan.gamification_api.application.usecases;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.entities.reward.Rarity;
import io.github.thallesyan.gamification_api.domain.entities.reward.RarityEnum;
import io.github.thallesyan.gamification_api.domain.services.AssociatePoints;
import io.github.thallesyan.gamification_api.domain.services.FindRarity;
import io.github.thallesyan.gamification_api.application.exceptions.PlatformNotFoundException;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.PlatformJpaPersistence;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RarityApplication {

    private final AssociatePoints associatePoints;
    private final FindRarity findRarity;
    private final PlatformJpaPersistence platformJpaPersistence;

    public RarityApplication(AssociatePoints associatePoints,
                             FindRarity findRarity,
                             PlatformJpaPersistence platformJpaPersistence) {
        this.associatePoints = associatePoints;
        this.findRarity = findRarity;
        this.platformJpaPersistence = platformJpaPersistence;
    }

    public Rarity associatePoints(RarityEnum rarityEnum, Integer points, Platform platform) {
        Rarity rarity = new Rarity(rarityEnum, points, platform);
        return associatePoints.associatePoints(rarity);
    }

    public void associatePointsList(List<Rarity> rarities) {
        validatePlatformsExist(rarities);
        rarities.forEach(associatePoints::associatePoints);
    }

    public List<Rarity> getAllRaritiesByPlatform(Platform platform) {
        return findRarity.findByPlatform(platform);
    }

    private void validatePlatformsExist(List<Rarity> rarities) {
        Set<String> platformNames = rarities.stream()
                .map(Rarity::getPlatform)
                .filter(Objects::nonNull)
                .map(Platform::getName)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        platformNames.forEach(name -> {
            boolean platformExists = platformJpaPersistence.existsById(name);
            if (!platformExists) {
                throw new PlatformNotFoundException(name);
            }
        });
    }
}


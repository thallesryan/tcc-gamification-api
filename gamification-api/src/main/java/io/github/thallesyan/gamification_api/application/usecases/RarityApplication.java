package io.github.thallesyan.gamification_api.application.usecases;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.entities.reward.Rarity;
import io.github.thallesyan.gamification_api.domain.entities.reward.RarityEnum;
import io.github.thallesyan.gamification_api.domain.services.AssociatePoints;
import io.github.thallesyan.gamification_api.domain.services.FindRarity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RarityApplication {

    private final AssociatePoints associatePoints;
    private final FindRarity findRarity;

    public RarityApplication(AssociatePoints associatePoints, FindRarity findRarity) {
        this.associatePoints = associatePoints;
        this.findRarity = findRarity;
    }

    public Rarity associatePoints(RarityEnum rarityEnum, Integer points, Platform platform) {
        Rarity rarity = new Rarity(rarityEnum, points, platform);
        return associatePoints.associatePoints(rarity);
    }

    public void associatePointsList(List<Rarity> rarities) {
        rarities.forEach(associatePoints::associatePoints);
    }

    public List<Rarity> getAllRaritiesByPlatform(Platform platform) {
        return findRarity.findByPlatform(platform);
    }
}


package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.CreateRarityBoundary;
import io.github.thallesyan.gamification_api.domain.entities.reward.Rarity;
import io.github.thallesyan.gamification_api.domain.services.AssociatePoints;
import org.springframework.stereotype.Service;

@Service
public class AssociatePointsImpl implements AssociatePoints {

    private final CreateRarityBoundary createRarityBoundary;

    public AssociatePointsImpl(CreateRarityBoundary createRarityBoundary) {
        this.createRarityBoundary = createRarityBoundary;
    }

    @Override
    public Rarity associatePoints(Rarity rarity) {
        return createRarityBoundary.createOrUpdateRarity(rarity);
    }
}


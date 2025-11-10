package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.CreateRarityBoundary;
import io.github.thallesyan.gamification_api.domain.entities.reward.Rarity;
import io.github.thallesyan.gamification_api.domain.services.CreateRarity;
import org.springframework.stereotype.Service;

@Service
public class CreateRarityImpl implements CreateRarity {

    private final CreateRarityBoundary createRarityBoundary;

    public CreateRarityImpl(CreateRarityBoundary createRarityBoundary) {
        this.createRarityBoundary = createRarityBoundary;
    }

    @Override
    public Rarity createRarity(Rarity rarity) {
        return createRarityBoundary.createOrUpdateRarity(rarity);
    }
}


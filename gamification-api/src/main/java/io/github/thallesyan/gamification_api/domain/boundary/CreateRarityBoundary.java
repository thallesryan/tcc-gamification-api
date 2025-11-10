package io.github.thallesyan.gamification_api.domain.boundary;

import io.github.thallesyan.gamification_api.domain.entities.reward.Rarity;

public interface CreateRarityBoundary {
    Rarity createOrUpdateRarity(Rarity rarity);
}


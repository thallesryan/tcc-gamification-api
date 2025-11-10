package io.github.thallesyan.gamification_api.domain.boundary;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.entities.reward.Rarity;

import java.util.List;
import java.util.Optional;

public interface FindRarityBoundary {
    List<Rarity> findByPlatform(Platform platform);
    
    Optional<Rarity> findByRarityEnumAndPlatform(io.github.thallesyan.gamification_api.domain.entities.reward.RarityEnum rarityEnum, Platform platform);
}


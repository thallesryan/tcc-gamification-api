package io.github.thallesyan.gamification_api.domain.services;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.entities.reward.Rarity;

import java.util.List;

public interface FindRarity {
    List<Rarity> findByPlatform(Platform platform);
}


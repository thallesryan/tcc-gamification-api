package io.github.thallesyan.gamification_api.domain.services;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.entities.reward.Rarity;
import io.github.thallesyan.gamification_api.domain.entities.reward.RarityEnum;

import java.util.List;
import java.util.Optional;

public interface FindRarity {
    List<Rarity> findByPlatform(Platform platform);
    Optional<Rarity> findByRarityEnumAndPlatform(RarityEnum rarityEnum, Platform platform);
}


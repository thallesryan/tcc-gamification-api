package io.github.thallesyan.gamification_api.domain.services;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.entities.reward.Badge;
import io.github.thallesyan.gamification_api.domain.entities.reward.RarityEnum;

import java.util.List;

public interface FindBadgesByRarity {
    List<Badge> findBadgesByRarity(RarityEnum rarityEnum, Platform platform);
}


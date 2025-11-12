package io.github.thallesyan.gamification_api.domain.boundary;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.entities.reward.Badge;
import io.github.thallesyan.gamification_api.domain.entities.reward.RarityEnum;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FindBadgeBoundary {
    List<Badge> findBadgesByRarity(RarityEnum rarityEnum, Platform platform);
    Optional<Badge> findBadgeById(UUID identifier);
    List<Badge> findAllBadges();
}


package io.github.thallesyan.gamification_api.domain.services;

import io.github.thallesyan.gamification_api.domain.entities.reward.Badge;

import java.util.Optional;
import java.util.UUID;

public interface FindBadge {
    Optional<Badge> findBadgeById(UUID identifier);
}


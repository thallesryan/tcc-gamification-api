package io.github.thallesyan.gamification_api.domain.services;

import io.github.thallesyan.gamification_api.domain.entities.reward.Badge;

import java.util.UUID;

public interface UpdateBadge {
    Badge updateBadge(UUID badgeId, Badge badge);
}


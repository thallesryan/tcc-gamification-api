package io.github.thallesyan.gamification_api.domain.boundary;

import io.github.thallesyan.gamification_api.domain.entities.reward.Badge;

import java.util.UUID;

public interface UpdateBadgeBoundary {
    Badge updateBadge(UUID badgeId, Badge badge);
}


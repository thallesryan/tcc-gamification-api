package io.github.thallesyan.gamification_api.domain.boundary;

import io.github.thallesyan.gamification_api.domain.entities.reward.Badge;

@FunctionalInterface
public interface CreateBadgeBoundary {
    Badge createBadge(Badge badge);
}


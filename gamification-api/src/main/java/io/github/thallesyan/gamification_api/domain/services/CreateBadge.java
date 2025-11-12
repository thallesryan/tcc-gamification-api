package io.github.thallesyan.gamification_api.domain.services;

import io.github.thallesyan.gamification_api.domain.entities.reward.Badge;

@FunctionalInterface
public interface CreateBadge {
    Badge createBadge(Badge badge);
}


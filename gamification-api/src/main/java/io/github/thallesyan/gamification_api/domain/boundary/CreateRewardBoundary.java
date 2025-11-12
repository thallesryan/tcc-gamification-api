package io.github.thallesyan.gamification_api.domain.boundary;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Reward;

@FunctionalInterface
public interface CreateRewardBoundary {
    Reward createReward(Reward reward);
}


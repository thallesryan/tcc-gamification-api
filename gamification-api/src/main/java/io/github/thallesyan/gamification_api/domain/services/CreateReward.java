package io.github.thallesyan.gamification_api.domain.services;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Reward;

@FunctionalInterface
public interface CreateReward {
    Reward createReward(Reward reward);
}


package io.github.thallesyan.gamification_api.domain.boundary;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Reward;

import java.util.UUID;

public interface UpdateRewardBoundary {
    Reward updateReward(UUID rewardId, Reward reward);
    Reward updateReward(UUID rewardId, Reward reward, Boolean isActive);
}


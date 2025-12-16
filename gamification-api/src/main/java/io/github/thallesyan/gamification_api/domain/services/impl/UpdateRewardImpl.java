package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.UpdateRewardBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Reward;
import io.github.thallesyan.gamification_api.domain.services.UpdateReward;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateRewardImpl implements UpdateReward {

    private final UpdateRewardBoundary updateRewardBoundary;

    public UpdateRewardImpl(UpdateRewardBoundary updateRewardBoundary) {
        this.updateRewardBoundary = updateRewardBoundary;
    }

    @Override
    public Reward updateReward(UUID rewardId, Reward reward) {
        return updateRewardBoundary.updateReward(rewardId, reward);
    }

    @Override
    public Reward updateReward(UUID rewardId, Reward reward, Boolean isActive) {
        return updateRewardBoundary.updateReward(rewardId, reward, isActive);
    }
}


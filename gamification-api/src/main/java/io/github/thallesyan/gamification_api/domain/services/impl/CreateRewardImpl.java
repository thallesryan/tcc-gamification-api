package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.CreateRewardBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Reward;
import io.github.thallesyan.gamification_api.domain.services.CreateReward;
import org.springframework.stereotype.Service;

@Service
public class CreateRewardImpl implements CreateReward {

    private final CreateRewardBoundary createRewardBoundary;

    public CreateRewardImpl(CreateRewardBoundary createRewardBoundary) {
        this.createRewardBoundary = createRewardBoundary;
    }

    @Override
    public Reward createReward(Reward reward) {
        return createRewardBoundary.createReward(reward);
    }
}


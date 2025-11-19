package io.github.thallesyan.gamification_api.domain.boundary;

import io.github.thallesyan.gamification_api.domain.entities.progress.UserReward;

public interface AssignRewardToUserBoundary {
    UserReward assignReward(UserReward userReward);
}


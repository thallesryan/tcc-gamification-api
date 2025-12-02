package io.github.thallesyan.gamification_api.domain.boundary;

import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserReward;

import java.util.List;

public interface FindUserRewardsBoundary {
    List<UserReward> findByUser(User user);
}





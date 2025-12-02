package io.github.thallesyan.gamification_api.domain.services;

import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserReward;

import java.util.List;

@FunctionalInterface
public interface FindUserRewards {
    List<UserReward> findByUser(User user);
}





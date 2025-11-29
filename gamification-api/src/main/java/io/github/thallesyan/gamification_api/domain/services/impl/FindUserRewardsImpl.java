package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.FindUserRewardsBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserReward;
import io.github.thallesyan.gamification_api.domain.services.FindUserRewards;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindUserRewardsImpl implements FindUserRewards {

    private final FindUserRewardsBoundary findUserRewardsBoundary;

    public FindUserRewardsImpl(FindUserRewardsBoundary findUserRewardsBoundary) {
        this.findUserRewardsBoundary = findUserRewardsBoundary;
    }

    @Override
    public List<UserReward> findByUser(User user) {
        return findUserRewardsBoundary.findByUser(user);
    }
}



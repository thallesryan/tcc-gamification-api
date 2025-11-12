package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.FindRewardBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Reward;
import io.github.thallesyan.gamification_api.domain.services.FindRewards;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FindRewardsImpl implements FindRewards {

    private final FindRewardBoundary findRewardBoundary;

    public FindRewardsImpl(FindRewardBoundary findRewardBoundary) {
        this.findRewardBoundary = findRewardBoundary;
    }

    @Override
    public List<Reward> findAllRewards() {
        return findRewardBoundary.findAllRewards();
    }

    @Override
    public Optional<Reward> findRewardById(UUID identifier) {
        return findRewardBoundary.findRewardById(identifier);
    }
}


package io.github.thallesyan.gamification_api.domain.services;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Reward;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FindRewards {
    List<Reward> findAllRewards();
    Optional<Reward> findRewardById(UUID identifier);
}


package io.github.thallesyan.gamification_api.domain.services;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Goal;

import java.util.Optional;
import java.util.UUID;

public interface FindGoal {
    Optional<Goal> byGoalId(UUID goalId);
}


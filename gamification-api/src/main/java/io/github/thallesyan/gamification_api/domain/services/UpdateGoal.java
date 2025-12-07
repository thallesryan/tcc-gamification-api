package io.github.thallesyan.gamification_api.domain.services;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Goal;

import java.util.UUID;

public interface UpdateGoal {
    Goal updateGoal(UUID goalId, Goal goal);
}


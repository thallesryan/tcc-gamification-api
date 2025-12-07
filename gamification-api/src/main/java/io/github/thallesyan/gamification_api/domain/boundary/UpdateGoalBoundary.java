package io.github.thallesyan.gamification_api.domain.boundary;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Goal;

import java.util.UUID;

public interface UpdateGoalBoundary {
    Goal updateGoal(UUID goalId, Goal goal);
}


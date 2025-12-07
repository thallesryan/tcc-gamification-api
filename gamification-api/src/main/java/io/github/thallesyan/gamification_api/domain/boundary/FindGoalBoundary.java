package io.github.thallesyan.gamification_api.domain.boundary;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Goal;

import java.util.Optional;
import java.util.UUID;

public interface FindGoalBoundary {
    Optional<Goal> findGoalById(UUID goalId);
}


package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.UpdateGoalBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Goal;
import io.github.thallesyan.gamification_api.domain.services.UpdateGoal;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateGoalImpl implements UpdateGoal {

    private final UpdateGoalBoundary updateGoalBoundary;

    public UpdateGoalImpl(UpdateGoalBoundary updateGoalBoundary) {
        this.updateGoalBoundary = updateGoalBoundary;
    }

    @Override
    public Goal updateGoal(UUID goalId, Goal goal) {
        return updateGoalBoundary.updateGoal(goalId, goal);
    }
}


package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.FindGoalBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Goal;
import io.github.thallesyan.gamification_api.domain.services.FindGoal;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindGoalImpl implements FindGoal {

    private final FindGoalBoundary findGoalBoundary;

    public FindGoalImpl(FindGoalBoundary findGoalBoundary) {
        this.findGoalBoundary = findGoalBoundary;
    }

    @Override
    public Optional<Goal> byGoalId(UUID goalId) {
        return findGoalBoundary.findGoalById(goalId);
    }
}


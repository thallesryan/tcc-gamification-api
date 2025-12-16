package io.github.thallesyan.gamification_api.application.usecases;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Goal;
import io.github.thallesyan.gamification_api.domain.services.FindGoal;
import io.github.thallesyan.gamification_api.domain.services.UpdateGoal;
import io.github.thallesyan.gamification_api.application.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class GoalApplication {

    private final FindGoal findGoal;
    private final UpdateGoal updateGoal;

    public GoalApplication(FindGoal findGoal, UpdateGoal updateGoal) {
        this.findGoal = findGoal;
        this.updateGoal = updateGoal;
    }

    public Optional<Goal> findById(UUID goalId) {
        return findGoal.byGoalId(goalId);
    }

    public Goal updateGoal(UUID goalId, Goal goal) {
        findGoal.byGoalId(goalId)
                .orElseThrow(() -> new EntityNotFoundException("Goal", goalId));
        return updateGoal.updateGoal(goalId, goal);
    }
}


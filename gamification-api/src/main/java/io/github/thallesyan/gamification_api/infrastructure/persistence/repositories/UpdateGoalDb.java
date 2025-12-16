package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.UpdateGoalBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Goal;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.GoalJpaPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.GoalPersistenceMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateGoalDb implements UpdateGoalBoundary {

    private final GoalJpaPersistence goalJpaPersistence;
    private final GoalPersistenceMapper goalPersistenceMapper;

    public UpdateGoalDb(GoalJpaPersistence goalJpaPersistence, GoalPersistenceMapper goalPersistenceMapper) {
        this.goalJpaPersistence = goalJpaPersistence;
        this.goalPersistenceMapper = goalPersistenceMapper;
    }

    @Override
    public Goal updateGoal(UUID goalId, Goal goal) {
        var existingGoalJPA = goalJpaPersistence.findById(goalId)
                .orElseThrow(() -> new RuntimeException("Goal not found with id: " + goalId));

        // Atualizar campos apenas se não forem null (PATCH)
        if (goal.getTitle() != null) {
            existingGoalJPA.setTitle(goal.getTitle());
        }
        if (goal.getDescription() != null) {
            existingGoalJPA.setDescription(goal.getDescription());
        }
        // Order não pode ser atualizado via PATCH

        var updatedGoalJPA = goalJpaPersistence.save(existingGoalJPA);
        return goalPersistenceMapper.toGoal(updatedGoalJPA);
    }
}


package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.FindGoalBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Goal;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.GoalJpaPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.GoalPersistenceMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindGoalDb implements FindGoalBoundary {

    private final GoalJpaPersistence goalJpaPersistence;
    private final GoalPersistenceMapper goalPersistenceMapper;

    public FindGoalDb(GoalJpaPersistence goalJpaPersistence, GoalPersistenceMapper goalPersistenceMapper) {
        this.goalJpaPersistence = goalJpaPersistence;
        this.goalPersistenceMapper = goalPersistenceMapper;
    }

    @Override
    public Optional<Goal> findGoalById(UUID goalId) {
        return goalJpaPersistence.findById(goalId)
                .map(goalPersistenceMapper::toGoal);
    }
}


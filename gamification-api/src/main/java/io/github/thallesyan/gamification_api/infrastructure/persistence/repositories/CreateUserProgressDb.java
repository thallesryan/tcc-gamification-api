package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.CreateUserProgressBoundary;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserProgress;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.UserProgressJpaPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.UserProgressPersistenceMapper;
import org.springframework.stereotype.Service;

@Service
public class CreateUserProgressDb implements CreateUserProgressBoundary {

    private final UserProgressJpaPersistence userProgressJpaPersistence;
    private final UserProgressPersistenceMapper userProgressPersistenceMapper;

    public CreateUserProgressDb(UserProgressJpaPersistence userProgressJpaPersistence, UserProgressPersistenceMapper userProgressPersistenceMapper) {
        this.userProgressJpaPersistence = userProgressJpaPersistence;
        this.userProgressPersistenceMapper = userProgressPersistenceMapper;
    }

    @Override
    public UserProgress createUserProgress(UserProgress userProgress) {
        var userProgressJPA = userProgressPersistenceMapper.toUserProgressJPA(userProgress);
        var userProgressCreatedDb = userProgressJpaPersistence.save(userProgressJPA);
        return userProgressPersistenceMapper.toUserProgress(userProgressCreatedDb);
    }
}


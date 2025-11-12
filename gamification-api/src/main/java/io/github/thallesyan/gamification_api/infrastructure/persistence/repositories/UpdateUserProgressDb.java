package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.UpdateUserProgressBoundary;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserProgress;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.UserProgressJpaPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.UserProgressPersistenceMapper;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserProgressDb implements UpdateUserProgressBoundary {

    private final UserProgressJpaPersistence userProgressJpaPersistence;
    private final UserProgressPersistenceMapper userProgressPersistenceMapper;

    public UpdateUserProgressDb(UserProgressJpaPersistence userProgressJpaPersistence, UserProgressPersistenceMapper userProgressPersistenceMapper) {
        this.userProgressJpaPersistence = userProgressJpaPersistence;
        this.userProgressPersistenceMapper = userProgressPersistenceMapper;
    }

    @Override
    public UserProgress updateUserProgress(UserProgress userProgress) {
        var userProgressJPA = userProgressPersistenceMapper.toUserProgressJPA(userProgress);
        var userProgressUpdatedDb = userProgressJpaPersistence.save(userProgressJPA);
        return userProgressPersistenceMapper.toUserProgress(userProgressUpdatedDb);
    }
}


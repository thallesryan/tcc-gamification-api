package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.FindUserProgressBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserProgress;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.UserProgressJpaPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.UserProgressPersistenceMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindUserProgressDb implements FindUserProgressBoundary {

    private final UserProgressJpaPersistence userProgressJpaPersistence;
    private final UserProgressPersistenceMapper userProgressPersistenceMapper;

    public FindUserProgressDb(UserProgressJpaPersistence userProgressJpaPersistence, UserProgressPersistenceMapper userProgressPersistenceMapper) {
        this.userProgressJpaPersistence = userProgressJpaPersistence;
        this.userProgressPersistenceMapper = userProgressPersistenceMapper;
    }

    @Override
    public Optional<UserProgress> findByUser(User user) {
        return userProgressJpaPersistence.findByUser_Identifier(user.getIdentifier())
                .map(userProgressPersistenceMapper::toUserProgress);
    }
}


package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.FindUserByEmailBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.UserJpaPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.UserPersistenceMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindUserByEmailDb implements FindUserByEmailBoundary {

    private final UserJpaPersistence userJpaPersistence;
    private final UserPersistenceMapper userPersistenceMapper;

    public FindUserByEmailDb(UserJpaPersistence userJpaPersistence, UserPersistenceMapper userPersistenceMapper) {
        this.userJpaPersistence = userJpaPersistence;
        this.userPersistenceMapper = userPersistenceMapper;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userJpaPersistence.findByEmail(email)
                .map(userPersistenceMapper::toUser);
    }
}

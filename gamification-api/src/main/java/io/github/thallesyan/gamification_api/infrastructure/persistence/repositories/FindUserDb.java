package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.FindUserBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.UserJpaPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.UserPersistenceMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindUserDb implements FindUserBoundary {

    private final UserJpaPersistence userJpaPersistence;
    private final UserPersistenceMapper userPersistenceMapper;

    public FindUserDb(UserJpaPersistence userJpaPersistence, UserPersistenceMapper userPersistenceMapper) {
        this.userJpaPersistence = userJpaPersistence;
        this.userPersistenceMapper = userPersistenceMapper;
    }

    @Override
    public Optional<User> ByEmail(String email) {
        return userJpaPersistence.findByEmail(email)
                .map(userPersistenceMapper::toUser);
    }

    @Override
    public boolean verifyUserExistsByEmail(String email) {
        return userJpaPersistence.existsByEmail(email);
    }
}

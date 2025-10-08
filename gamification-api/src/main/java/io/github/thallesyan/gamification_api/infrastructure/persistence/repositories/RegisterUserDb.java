package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.RegisterUserBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.UserJpaPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.UserPersistenceMapper;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserDb implements RegisterUserBoundary {

    private final UserJpaPersistence userJpaPersistence;
    private final UserPersistenceMapper userPersistenceMapper;

    public RegisterUserDb(UserJpaPersistence userJpaPersistence, UserPersistenceMapper userPersistenceMapper) {
        this.userJpaPersistence = userJpaPersistence;
        this.userPersistenceMapper = userPersistenceMapper;
    }

    @Override
    public User registerUser(User user) {
        var userJPA = userPersistenceMapper.toUserJPA(user);

        var userCreatedDb = userJpaPersistence.save(userJPA);
        return userPersistenceMapper.toUser(userCreatedDb);
    }
}

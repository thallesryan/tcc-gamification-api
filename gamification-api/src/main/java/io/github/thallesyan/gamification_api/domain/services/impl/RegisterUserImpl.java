package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.RegisterUserBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.domain.services.RegisterUser;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserImpl implements RegisterUser {

    private final RegisterUserBoundary registerUserBoundary;

    public RegisterUserImpl(RegisterUserBoundary registerUserBoundary) {
        this.registerUserBoundary = registerUserBoundary;
    }

    @Override
    public User registerUser(User user) {
        return registerUserBoundary.registerUser(user);
    }
}

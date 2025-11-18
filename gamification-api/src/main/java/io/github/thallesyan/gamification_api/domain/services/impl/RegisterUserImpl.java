package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.RegisterUserBoundary;
import io.github.thallesyan.gamification_api.domain.exceptions.UserAlreadyExistsException;

import io.github.thallesyan.gamification_api.domain.entities.foundation.User;

import io.github.thallesyan.gamification_api.domain.services.RegisterUser;
import io.github.thallesyan.gamification_api.domain.services.VerifyUserExists;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserImpl implements RegisterUser {

    private final RegisterUserBoundary registerUserBoundary;
    private final VerifyUserExists verifyUserExists;

    public RegisterUserImpl(RegisterUserBoundary registerUserBoundary,
                            VerifyUserExists verifyUserExists) {
        this.registerUserBoundary = registerUserBoundary;
        this.verifyUserExists = verifyUserExists;
    }

    @Override
    public User registerUser(User user) {
        if (verifyUserExists.byEmail(user.getEmail())) {
            throw new UserAlreadyExistsException(user.getEmail());
        }

        return registerUserBoundary.registerUser(user);
    }
}

package io.github.thallesyan.gamification_api.domain.boundary;

import io.github.thallesyan.gamification_api.domain.entities.foundation.User;

@FunctionalInterface
public interface RegisterUserBoundary {

    User registerUser(User user);
}

package io.github.thallesyan.gamification_api.domain.services;

import io.github.thallesyan.gamification_api.domain.entities.foundation.User;

@FunctionalInterface
public interface RegisterUser {

    User registerUser(User user);
}

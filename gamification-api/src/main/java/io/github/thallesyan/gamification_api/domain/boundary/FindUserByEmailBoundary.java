package io.github.thallesyan.gamification_api.domain.boundary;

import io.github.thallesyan.gamification_api.domain.entities.foundation.User;

import java.util.Optional;

@FunctionalInterface
public interface FindUserByEmailBoundary {

    Optional<User> findUserByEmail(String email);
}

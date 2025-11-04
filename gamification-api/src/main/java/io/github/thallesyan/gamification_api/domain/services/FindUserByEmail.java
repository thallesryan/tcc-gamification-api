package io.github.thallesyan.gamification_api.domain.services;

import io.github.thallesyan.gamification_api.domain.entities.foundation.User;

import java.util.Optional;

@FunctionalInterface
public interface FindUserByEmail {

    Optional<User> byEmail(String email, String platform);
}

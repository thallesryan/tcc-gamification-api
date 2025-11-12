package io.github.thallesyan.gamification_api.domain.services;

import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserProgress;

import java.util.Optional;

@FunctionalInterface
public interface FindUserProgress {
    Optional<UserProgress> findByUser(User user);
}


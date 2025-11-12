package io.github.thallesyan.gamification_api.domain.boundary;

import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserProgress;

import java.util.Optional;

public interface FindUserProgressBoundary {
    Optional<UserProgress> findByUser(User user);
}


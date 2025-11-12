package io.github.thallesyan.gamification_api.domain.services;

import io.github.thallesyan.gamification_api.domain.entities.progress.UserProgress;

@FunctionalInterface
public interface UpdateUserProgress {
    UserProgress updateUserProgress(UserProgress userProgress);
}


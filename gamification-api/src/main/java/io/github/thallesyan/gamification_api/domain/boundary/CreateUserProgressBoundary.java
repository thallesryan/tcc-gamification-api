package io.github.thallesyan.gamification_api.domain.boundary;

import io.github.thallesyan.gamification_api.domain.entities.progress.UserProgress;

public interface CreateUserProgressBoundary {
    UserProgress createUserProgress(UserProgress userProgress);
}


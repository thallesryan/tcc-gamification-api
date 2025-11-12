package io.github.thallesyan.gamification_api.domain.boundary;

import io.github.thallesyan.gamification_api.domain.entities.progress.UserProgress;

public interface UpdateUserProgressBoundary {
    UserProgress updateUserProgress(UserProgress userProgress);
}


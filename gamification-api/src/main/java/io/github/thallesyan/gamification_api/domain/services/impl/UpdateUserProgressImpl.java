package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.UpdateUserProgressBoundary;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserProgress;
import io.github.thallesyan.gamification_api.domain.services.UpdateUserProgress;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserProgressImpl implements UpdateUserProgress {

    private final UpdateUserProgressBoundary updateUserProgressBoundary;

    public UpdateUserProgressImpl(UpdateUserProgressBoundary updateUserProgressBoundary) {
        this.updateUserProgressBoundary = updateUserProgressBoundary;
    }

    @Override
    public UserProgress updateUserProgress(UserProgress userProgress) {
        return updateUserProgressBoundary.updateUserProgress(userProgress);
    }
}


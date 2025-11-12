package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.CreateUserProgressBoundary;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserProgress;
import io.github.thallesyan.gamification_api.domain.services.CreateUserProgress;
import org.springframework.stereotype.Service;

@Service
public class CreateUserProgressImpl implements CreateUserProgress {

    private final CreateUserProgressBoundary createUserProgressBoundary;

    public CreateUserProgressImpl(CreateUserProgressBoundary createUserProgressBoundary) {
        this.createUserProgressBoundary = createUserProgressBoundary;
    }

    @Override
    public UserProgress createUserProgress(UserProgress userProgress) {
        return createUserProgressBoundary.createUserProgress(userProgress);
    }
}


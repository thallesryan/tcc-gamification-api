package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.FindUserProgressBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserProgress;
import io.github.thallesyan.gamification_api.domain.services.FindUserProgress;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindUserProgressImpl implements FindUserProgress {

    private final FindUserProgressBoundary findUserProgressBoundary;

    public FindUserProgressImpl(FindUserProgressBoundary findUserProgressBoundary) {
        this.findUserProgressBoundary = findUserProgressBoundary;
    }

    @Override
    public Optional<UserProgress> findByUser(User user) {
        return findUserProgressBoundary.findByUser(user);
    }
}


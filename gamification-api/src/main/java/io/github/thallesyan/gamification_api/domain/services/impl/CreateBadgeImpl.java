package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.CreateBadgeBoundary;
import io.github.thallesyan.gamification_api.domain.entities.reward.Badge;
import io.github.thallesyan.gamification_api.domain.services.CreateBadge;
import org.springframework.stereotype.Service;

@Service
public class CreateBadgeImpl implements CreateBadge {

    private final CreateBadgeBoundary createBadgeBoundary;

    public CreateBadgeImpl(CreateBadgeBoundary createBadgeBoundary) {
        this.createBadgeBoundary = createBadgeBoundary;
    }

    @Override
    public Badge createBadge(Badge badge) {
        return createBadgeBoundary.createBadge(badge);
    }
}


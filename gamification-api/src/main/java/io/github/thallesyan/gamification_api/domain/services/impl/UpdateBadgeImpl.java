package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.UpdateBadgeBoundary;
import io.github.thallesyan.gamification_api.domain.entities.reward.Badge;
import io.github.thallesyan.gamification_api.domain.services.UpdateBadge;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateBadgeImpl implements UpdateBadge {

    private final UpdateBadgeBoundary updateBadgeBoundary;

    public UpdateBadgeImpl(UpdateBadgeBoundary updateBadgeBoundary) {
        this.updateBadgeBoundary = updateBadgeBoundary;
    }

    @Override
    public Badge updateBadge(UUID badgeId, Badge badge) {
        return updateBadgeBoundary.updateBadge(badgeId, badge);
    }
}


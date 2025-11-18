package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.FindBadgeBoundary;
import io.github.thallesyan.gamification_api.domain.entities.reward.Badge;
import io.github.thallesyan.gamification_api.domain.services.FindBadge;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindBadgeImpl implements FindBadge {

    private final FindBadgeBoundary findBadgeBoundary;

    public FindBadgeImpl(FindBadgeBoundary findBadgeBoundary) {
        this.findBadgeBoundary = findBadgeBoundary;
    }

    @Override
    public Optional<Badge> findBadgeById(UUID identifier) {
        return findBadgeBoundary.findBadgeById(identifier);
    }
}


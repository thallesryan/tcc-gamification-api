package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.UpdateBadgeBoundary;
import io.github.thallesyan.gamification_api.domain.entities.reward.Badge;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.BadgeJpaPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.BadgePersistenceMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateBadgeDb implements UpdateBadgeBoundary {

    private final BadgeJpaPersistence badgeJpaPersistence;
    private final BadgePersistenceMapper badgePersistenceMapper;

    public UpdateBadgeDb(BadgeJpaPersistence badgeJpaPersistence, 
                         BadgePersistenceMapper badgePersistenceMapper) {
        this.badgeJpaPersistence = badgeJpaPersistence;
        this.badgePersistenceMapper = badgePersistenceMapper;
    }

    @Override
    public Badge updateBadge(UUID badgeId, Badge badge) {
        var existingBadgeJPA = badgeJpaPersistence.findById(badgeId)
                .orElseThrow(() -> new RuntimeException("Badge not found with id: " + badgeId));

        // Atualizar apenas campos fornecidos (PATCH)
        if (badge.getName() != null) {
            existingBadgeJPA.setName(badge.getName());
        }
        if (badge.getDescription() != null) {
            existingBadgeJPA.setDescription(badge.getDescription());
        }

        var updatedBadgeJPA = badgeJpaPersistence.save(existingBadgeJPA);
        return badgePersistenceMapper.toModel(updatedBadgeJPA);
    }
}


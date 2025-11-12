package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.CreateBadgeBoundary;
import io.github.thallesyan.gamification_api.domain.entities.reward.Badge;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.BadgeJpaPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.BadgePersistenceMapper;
import org.springframework.stereotype.Service;

@Service
public class CreateBadgeDb implements CreateBadgeBoundary {

    private final BadgeJpaPersistence badgeJpaPersistence;
    private final BadgePersistenceMapper badgePersistenceMapper;

    public CreateBadgeDb(BadgeJpaPersistence badgeJpaPersistence, BadgePersistenceMapper badgePersistenceMapper) {
        this.badgeJpaPersistence = badgeJpaPersistence;
        this.badgePersistenceMapper = badgePersistenceMapper;
    }

    @Override
    public Badge createBadge(Badge badge) {
        var badgeJPA = badgePersistenceMapper.toJPAEntity(badge);
        var badgeCreatedDb = badgeJpaPersistence.save(badgeJPA);
        return badgePersistenceMapper.toModel(badgeCreatedDb);
    }
}


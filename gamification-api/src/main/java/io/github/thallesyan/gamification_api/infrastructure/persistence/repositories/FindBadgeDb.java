package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.FindBadgeBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.entities.reward.Badge;
import io.github.thallesyan.gamification_api.domain.entities.reward.RarityEnum;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.BadgeJpaPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.PlatformJPA;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.BadgePersistenceMapper;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.PlatformPersistenceMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FindBadgeDb implements FindBadgeBoundary {

    private final BadgeJpaPersistence badgeJpaPersistence;
    private final BadgePersistenceMapper badgePersistenceMapper;
    private final PlatformPersistenceMapper platformPersistenceMapper;

    public FindBadgeDb(BadgeJpaPersistence badgeJpaPersistence, 
                      BadgePersistenceMapper badgePersistenceMapper,
                      PlatformPersistenceMapper platformPersistenceMapper) {
        this.badgeJpaPersistence = badgeJpaPersistence;
        this.badgePersistenceMapper = badgePersistenceMapper;
        this.platformPersistenceMapper = platformPersistenceMapper;
    }

    @Override
    public List<Badge> findBadgesByRarity(RarityEnum rarityEnum, Platform platform) {
        PlatformJPA platformJPA = platformPersistenceMapper.toJPAEntity(platform);
        return badgeJpaPersistence.findByRarityEnumAndPlatform(rarityEnum, platformJPA).stream()
                .map(badgePersistenceMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Badge> findBadgeById(UUID identifier) {
        return badgeJpaPersistence.findById(identifier)
                .map(badgePersistenceMapper::toModel);
    }

    @Override
    public List<Badge> findAllBadges() {
        return badgeJpaPersistence.findAll().stream()
                .map(badgePersistenceMapper::toModel)
                .collect(Collectors.toList());
    }
}


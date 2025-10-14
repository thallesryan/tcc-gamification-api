package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.CreatePlatformBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.services.CreatePlatform;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.PlatformJpaPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.PlatformPersistenceMapper;
import org.springframework.stereotype.Service;

@Service
public class CreatePlatformDb implements CreatePlatformBoundary {

    private final PlatformJpaPersistence platformJpaPersistence;
    private final PlatformPersistenceMapper  platformPersistenceMapper;

    public CreatePlatformDb(PlatformJpaPersistence platformJpaPersistence, PlatformPersistenceMapper platformPersistenceMapper) {
        this.platformJpaPersistence = platformJpaPersistence;
        this.platformPersistenceMapper = platformPersistenceMapper;
    }

    @Override
    public Platform createPlatform(Platform platform) {
        return platformPersistenceMapper.toModel(platformJpaPersistence.save(platformPersistenceMapper.toJPAEntity(platform)));
    }
}

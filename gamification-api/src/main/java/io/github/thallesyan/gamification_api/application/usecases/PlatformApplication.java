package io.github.thallesyan.gamification_api.application.usecases;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.services.CreatePlatform;
import io.github.thallesyan.gamification_api.application.exceptions.PlatformNotFoundException;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.PlatformJpaPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.PlatformPersistenceMapper;
import org.springframework.stereotype.Component;

@Component
public class PlatformApplication {

    private final CreatePlatform createPlatform;
    private final PlatformJpaPersistence platformJpaPersistence;
    private final PlatformPersistenceMapper platformPersistenceMapper;

    public PlatformApplication(CreatePlatform createPlatform, 
                              PlatformJpaPersistence platformJpaPersistence,
                              PlatformPersistenceMapper platformPersistenceMapper) {
        this.createPlatform = createPlatform;
        this.platformJpaPersistence = platformJpaPersistence;
        this.platformPersistenceMapper = platformPersistenceMapper;
    }

    public Platform createPlatform(Platform platform){
        return createPlatform.createPlatform(platform);
    }

    public Platform findByName(String name) {
        return platformJpaPersistence.findById(name)
                .map(platformPersistenceMapper::toModel)
                .orElseThrow(() -> new PlatformNotFoundException(name));
    }
}

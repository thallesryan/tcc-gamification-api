package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.CreateMissionBoundary;
import io.github.thallesyan.gamification_api.domain.boundary.CreatePlatformBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.services.CreatePlatform;
import org.springframework.stereotype.Service;

@Service
public class CreatePlatformImpl implements CreatePlatform {

    private final CreatePlatformBoundary createPlatformBoundary;

    public CreatePlatformImpl(CreatePlatformBoundary createPlatformBoundary) {
        this.createPlatformBoundary = createPlatformBoundary;
    }

    @Override
    public Platform createPlatform(Platform platform) {
        return createPlatformBoundary.createPlatform(platform);
    }
}

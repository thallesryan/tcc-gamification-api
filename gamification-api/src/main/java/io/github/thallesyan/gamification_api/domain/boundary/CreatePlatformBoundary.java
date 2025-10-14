package io.github.thallesyan.gamification_api.domain.boundary;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;

public interface CreatePlatformBoundary {
    Platform createPlatform(Platform platform);
}

package io.github.thallesyan.gamification_api.domain.services;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.PlatformRequestDTO;

public interface CreatePlatform {
    Platform createPlatform(Platform platform);
}

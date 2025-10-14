package io.github.thallesyan.gamification_api.application.usecases;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.services.CreatePlatform;
import org.springframework.stereotype.Component;

@Component
public class PlatformApplication {

    private final CreatePlatform createPlatform;

    public PlatformApplication(CreatePlatform createPlatform) {
        this.createPlatform = createPlatform;
    }

    public Platform createPlatform(Platform platform){
        return createPlatform.createPlatform(platform);
    }
}

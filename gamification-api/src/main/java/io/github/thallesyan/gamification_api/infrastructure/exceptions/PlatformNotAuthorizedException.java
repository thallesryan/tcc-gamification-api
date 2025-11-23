package io.github.thallesyan.gamification_api.infrastructure.exceptions;

import org.springframework.http.HttpStatus;

public class PlatformNotAuthorizedException extends BaseException {
    
    public PlatformNotAuthorizedException(String platform) {
        super(
            String.format("User does not have access to platform: %s", platform),
            "PLATFORM_NOT_AUTHORIZED",
            HttpStatus.FORBIDDEN
        );
    }
}


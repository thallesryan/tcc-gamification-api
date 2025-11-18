package io.github.thallesyan.gamification_api.application.exceptions;

import io.github.thallesyan.gamification_api.infrastructure.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class PlatformNotFoundException extends BaseException {

    public PlatformNotFoundException(String platformName) {
        super(String.format("Platform with name %s not found", platformName),
                "PLATFORM_NOT_FOUND", HttpStatus.NOT_FOUND);
    }

    public PlatformNotFoundException(String message, Throwable cause) {
        super(message, "PLATFORM_NOT_FOUND", cause, HttpStatus.NOT_FOUND);
    }
}
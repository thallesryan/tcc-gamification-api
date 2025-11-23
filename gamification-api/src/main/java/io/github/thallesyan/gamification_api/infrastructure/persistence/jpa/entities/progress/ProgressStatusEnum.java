package io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress;

import io.github.thallesyan.gamification_api.infrastructure.exceptions.InvalidProgressStatusException;

public enum ProgressStatusEnum {
    ASSIGNED,
    IN_PROGRESS,
    COMPLETED,
    FAILED,
    CANCELLED;

    public static ProgressStatusEnum fromString(String value) {
        if (value == null || value.isBlank()) {
            throw new InvalidProgressStatusException(value);
        }

        try {
            return ProgressStatusEnum.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidProgressStatusException(value);
        }
    }
}

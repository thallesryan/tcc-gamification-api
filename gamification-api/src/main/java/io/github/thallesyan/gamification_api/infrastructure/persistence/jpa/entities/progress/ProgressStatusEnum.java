package io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress;

public enum ProgressStatusEnum {
    ASSIGNED,
    IN_PROGRESS,
    COMPLETED,
    FAILED,
    CANCELLED;

    public static ProgressStatusEnum fromString(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("O valor do status não pode ser nulo ou vazio.");
        }

        try {
            return ProgressStatusEnum.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Status inválido: " + value);
        }
    }
}

package io.github.thallesyan.gamification_api.infrastructure.web.dto.enums;

public enum RankingByEnum {
    LEVEL,
    GOALS_COMPLETED,
    MISSION_COMPLETED;

    public static RankingByEnum fromString(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("O valor do rankingBy não pode ser nulo ou vazio.");
        }

        try {
            return RankingByEnum.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("RankingBy inválido: " + value + ". Valores aceitos: LEVEL, GOALS_COMPLETED, MISSION_COMPLETED");
        }
    }
}


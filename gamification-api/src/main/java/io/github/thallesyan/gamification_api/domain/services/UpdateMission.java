package io.github.thallesyan.gamification_api.domain.services;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;

import java.util.UUID;

public interface UpdateMission {
    Mission updateMission(UUID missionId, Mission mission, Integer difficultyLevel);
}


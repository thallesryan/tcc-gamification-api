package io.github.thallesyan.gamification_api.domain.boundary;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;

import java.util.UUID;

public interface UpdateMissionBoundary {
    Mission updateMission(UUID missionId, Mission mission, Integer difficultyLevel);
}


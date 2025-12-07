package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.UpdateMissionBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.services.UpdateMission;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateMissionImpl implements UpdateMission {

    private final UpdateMissionBoundary updateMissionBoundary;

    public UpdateMissionImpl(UpdateMissionBoundary updateMissionBoundary) {
        this.updateMissionBoundary = updateMissionBoundary;
    }

    @Override
    public Mission updateMission(UUID missionId, Mission mission, Integer difficultyLevel) {
        return updateMissionBoundary.updateMission(missionId, mission, difficultyLevel);
    }
}


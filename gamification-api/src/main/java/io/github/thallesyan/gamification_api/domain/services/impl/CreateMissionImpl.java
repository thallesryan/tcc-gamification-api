package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.CreateMissionBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.services.CreateMission;
import org.springframework.stereotype.Service;

@Service
public class CreateMissionImpl implements CreateMission {

    private final CreateMissionBoundary createMissionBoundary;

    public CreateMissionImpl(CreateMissionBoundary createMissionBoundary) {
        this.createMissionBoundary = createMissionBoundary;
    }

    @Override
    public Mission createMission(Mission mission) {
        return createMissionBoundary.createMission(mission);
    }
}

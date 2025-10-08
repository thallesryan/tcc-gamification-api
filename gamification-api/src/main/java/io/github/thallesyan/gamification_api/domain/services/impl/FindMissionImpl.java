package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.FindMissionBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.services.FindMission;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindMissionImpl implements FindMission {

    private final FindMissionBoundary findMissionBoundary;

    public FindMissionImpl(FindMissionBoundary findMissionBoundary) {
        this.findMissionBoundary = findMissionBoundary;
    }

    @Override
    public Optional<Mission> byMissionId(UUID missionId) {
        return findMissionBoundary.findMissionById(missionId);
    }
}

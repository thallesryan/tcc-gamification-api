package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.FindMissionBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.entities.search.MissionsSearch;
import io.github.thallesyan.gamification_api.domain.services.FindMission;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public MissionsSearch byMissionsIds(List<UUID> missionId) {
        return new MissionsSearch(findMissionBoundary.byMissionsIds(missionId));
    }
}

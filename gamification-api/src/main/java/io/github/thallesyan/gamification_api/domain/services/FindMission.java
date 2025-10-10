package io.github.thallesyan.gamification_api.domain.services;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.entities.search.MissionsSearch;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface FindMission {

    Optional<Mission> byMissionId(UUID missionId);
    MissionsSearch byMissionsIds(List<UUID> missionId);
}

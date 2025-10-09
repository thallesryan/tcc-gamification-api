package io.github.thallesyan.gamification_api.domain.boundary;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface FindMissionBoundary {
    Optional<Mission> findMissionById(UUID missionId);
    Map<UUID, Optional<Mission>> byMissionsIds(List<UUID> missionId);
}

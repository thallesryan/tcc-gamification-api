package io.github.thallesyan.gamification_api.domain.boundary;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;

import java.util.Optional;
import java.util.UUID;

@FunctionalInterface
public interface FindMissionBoundary {

    Optional<Mission> findMissionById(UUID missionId);
}

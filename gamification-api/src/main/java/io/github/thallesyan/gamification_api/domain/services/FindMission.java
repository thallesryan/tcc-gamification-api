package io.github.thallesyan.gamification_api.domain.services;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;

import java.util.Optional;
import java.util.UUID;

@FunctionalInterface
public interface FindMission {

    Optional<Mission> byMissionId(UUID missionId);
}

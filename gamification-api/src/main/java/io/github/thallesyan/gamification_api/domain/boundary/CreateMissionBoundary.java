package io.github.thallesyan.gamification_api.domain.boundary;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;

@FunctionalInterface
public interface CreateMissionBoundary {

    Mission createMission(Mission mission);
}

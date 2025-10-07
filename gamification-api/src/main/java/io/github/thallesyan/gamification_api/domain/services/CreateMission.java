package io.github.thallesyan.gamification_api.domain.services;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;

@FunctionalInterface
public interface CreateMission {

    Mission createMission(Mission mission);
}

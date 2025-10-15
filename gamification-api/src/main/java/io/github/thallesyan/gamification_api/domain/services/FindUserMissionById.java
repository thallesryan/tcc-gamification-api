package io.github.thallesyan.gamification_api.domain.services;

import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;

@FunctionalInterface
public interface FindUserMissionById {
    UserMissionProgress byId(Integer id);
}

package io.github.thallesyan.gamification_api.domain.services;

import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;

public interface UpdateUserMission {
    UserMissionProgress startMission(UserMissionProgress userMissionProgress);
}

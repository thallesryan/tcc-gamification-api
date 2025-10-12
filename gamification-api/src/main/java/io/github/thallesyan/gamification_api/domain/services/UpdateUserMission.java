package io.github.thallesyan.gamification_api.domain.services;

import io.github.thallesyan.gamification_api.domain.entities.progress.UserMission;

public interface UpdateUserMission {
    UserMission startMission(UserMission userMission);
}

package io.github.thallesyan.gamification_api.domain.services;

import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.ProgressStatusEnum;

public interface FindUserMission {
    UserMissionProgress byMissionIdAndStatus(String userIdentifier, String missionIdentifier, ProgressStatusEnum progressStatusEnum);
}

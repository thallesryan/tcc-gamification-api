package io.github.thallesyan.gamification_api.domain.services;

import io.github.thallesyan.gamification_api.domain.entities.progress.UserMission;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.ProgressStatusEnum;

import java.util.Optional;

public interface FindUserMission {
    UserMission byMissionIdAndStatus(String userIdentifier, String missionIdentifier, ProgressStatusEnum progressStatusEnum);
}

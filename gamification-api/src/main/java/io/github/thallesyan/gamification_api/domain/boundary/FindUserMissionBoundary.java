package io.github.thallesyan.gamification_api.domain.boundary;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.ProgressStatusEnum;

import java.util.Optional;

public interface FindUserMissionBoundary {
    Optional<UserMissionProgress> byUserIdentifierAndMissionAndStatus(User user, Mission mission, ProgressStatusEnum progressStatusEnum);

}

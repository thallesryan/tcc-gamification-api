package io.github.thallesyan.gamification_api.domain.boundary;

import io.github.thallesyan.gamification_api.domain.entities.progress.UserMission;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionGoal;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.ProgressStatusEnum;

public interface UpdateUserMissionBoundary {
    UserMission updateMissionStatus(UserMission userMissionGoal, ProgressStatusEnum progressStatusEnum);
    UserMissionGoal updateGoalStatus(UserMissionGoal userMissionGoal, ProgressStatusEnum progressStatusEnum);
}

package io.github.thallesyan.gamification_api.domain.boundary;

import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionGoalProgress;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.ProgressStatusEnum;

public interface UpdateUserMissionBoundary {
    UserMissionProgress updateMissionStatus(UserMissionProgress userMissionProgressGoal, ProgressStatusEnum progressStatusEnum);
    UserMissionGoalProgress updateGoalStatus(UserMissionGoalProgress userMissionGoalProgress, ProgressStatusEnum progressStatusEnum);
}

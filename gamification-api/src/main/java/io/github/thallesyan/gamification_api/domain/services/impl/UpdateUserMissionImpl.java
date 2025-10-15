package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.UpdateUserMissionBoundary;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionGoalProgress;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.domain.services.UpdateUserMission;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.ProgressStatusEnum;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserMissionImpl implements UpdateUserMission {
    private final UpdateUserMissionBoundary userMissionBoundary;

    public UpdateUserMissionImpl(UpdateUserMissionBoundary userMissionBoundary) {
        this.userMissionBoundary = userMissionBoundary;
    }

    @Override
    public UserMissionProgress startMission(UserMissionProgress userMissionProgress) {
        var goalOne = userMissionProgress.getGoalProgressByOrder(1);

        var userGoalUpdated = userMissionBoundary.updateGoalStatus(goalOne, ProgressStatusEnum.IN_PROGRESS);
        var userMissionUpdated = userMissionBoundary.updateMissionStatus(userMissionProgress, ProgressStatusEnum.IN_PROGRESS);

        userMissionUpdated.setGoalProgressByOrder(userGoalUpdated, 1);

        return userMissionUpdated;
    }

    @Override
    public UserMissionProgress startGoal(UserMissionProgress userMissionProgress, UserMissionGoalProgress userMissionGoalProgress) {
        var userGoalUpdated = userMissionBoundary.updateGoalStatus(userMissionGoalProgress, ProgressStatusEnum.IN_PROGRESS);
        userMissionProgress.setGoalProgressByOrder(userGoalUpdated, userMissionGoalProgress.getGoal().getOrder());
        return userMissionProgress;
    }

    @Override
    public UserMissionProgress completeGoal(UserMissionProgress userMissionProgress, UserMissionGoalProgress userMissionGoalProgress) {
        var userGoalCompleted = userMissionBoundary.completeGoal(userMissionGoalProgress);
        userMissionProgress.setGoalProgressByOrder(userGoalCompleted, userMissionGoalProgress.getGoal().getOrder());
        return userMissionProgress;
    }

    @Override
    public UserMissionProgress completeMission(UserMissionProgress userMissionProgress) {
        return userMissionBoundary.completeMission(userMissionProgress);
    }
}

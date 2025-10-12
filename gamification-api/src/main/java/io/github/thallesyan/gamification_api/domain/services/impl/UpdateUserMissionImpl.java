package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.UpdateUserMissionBoundary;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMission;
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
    public UserMission startMission(UserMission userMission) {
        var goalOne = userMission.getGoalByOrder(1);
        //goalOne.setStatus(ProgressStatusEnum.IN_PROGRESS);
        //var userGoalUpdated = userMissionBoundary.updateGoalStatus(goalOne, ProgressStatusEnum.IN_PROGRESS);
        userMissionBoundary.updateMissionStatus(userMission, ProgressStatusEnum.IN_PROGRESS);

        return null;
    }
}

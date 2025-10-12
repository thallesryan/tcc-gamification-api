package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.UpdateUserMissionBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.enums.ProgressStatusEnumJPA;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMission;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionGoal;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.UserMissionGoalProgressPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.UserMissionProgressPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.ProgressStatusEnum;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.UserMissionProgressJPA;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserMissionDb implements UpdateUserMissionBoundary {

    private final UserMissionGoalProgressPersistence userMissionGoalProgressPersistence;
    private final UserMissionProgressPersistence userMissionProgressPersistence;

    public UpdateUserMissionDb(UserMissionGoalProgressPersistence userMissionGoalProgressPersistence, UserMissionProgressPersistence userMissionProgressPersistence) {
        this.userMissionGoalProgressPersistence = userMissionGoalProgressPersistence;
        this.userMissionProgressPersistence = userMissionProgressPersistence;
    }

    @Override
    public UserMission updateMissionStatus(UserMission userMission, ProgressStatusEnum progressStatusEnum) {
        var userMissionUpdated = userMissionProgressPersistence.updateStatus(userMission.getId(), ProgressStatusEnumJPA.getProgressJPAByProgressStatusEnum(progressStatusEnum));
        System.out.println(userMissionUpdated);
        return null;
    }

    @Override
    public UserMissionGoal updateGoalStatus(UserMissionGoal userMissionGoal, ProgressStatusEnum progressStatusEnum) {
        return null;
    }
}

package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.UpdateUserMissionBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.enums.ProgressStatusEnumJPA;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionGoalProgress;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.UserMissionGoalProgressPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.UserMissionProgressPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.ProgressStatusEnum;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UpdateUserMissionDb implements UpdateUserMissionBoundary {

    private final UserMissionGoalProgressPersistence userMissionGoalProgressPersistence;
    private final UserMissionProgressPersistence userMissionProgressPersistence;

    public UpdateUserMissionDb(UserMissionGoalProgressPersistence userMissionGoalProgressPersistence, UserMissionProgressPersistence userMissionProgressPersistence) {
        this.userMissionGoalProgressPersistence = userMissionGoalProgressPersistence;
        this.userMissionProgressPersistence = userMissionProgressPersistence;
    }

    @Override
    public UserMissionProgress updateMissionStatus(UserMissionProgress userMissionProgress, ProgressStatusEnum progressStatusEnum) {
        userMissionProgressPersistence.startUserMission(userMissionProgress.getId(), ProgressStatusEnumJPA.getProgressJPAByProgressStatusEnum(progressStatusEnum));

        userMissionProgress.setStatus(progressStatusEnum);
        userMissionProgress.setStartDate(LocalDateTime.now());

        return userMissionProgress;
    }

    @Override
    public UserMissionGoalProgress updateGoalStatus(UserMissionGoalProgress userMissionGoalProgress, ProgressStatusEnum progressStatusEnum) {
        userMissionGoalProgressPersistence.startUserMissionGoal(userMissionGoalProgress.getId(), ProgressStatusEnumJPA.getProgressJPAByProgressStatusEnum(progressStatusEnum));
        userMissionGoalProgress.setStatus(progressStatusEnum);
        userMissionGoalProgress.setStartDate(LocalDateTime.now());
        return userMissionGoalProgress;
    }
}

package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.UpdateUserMissionBoundary;
import io.github.thallesyan.gamification_api.domain.entities.progress.enums.ProgressStatusEnumJPA;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionGoalProgress;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.UserMissionGoalProgressPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.UserMissionProgressPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.ProgressStatusEnum;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.UserMissionGoalPersistenceMapper;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.UserMissionPersistenceMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UpdateUserMissionDb implements UpdateUserMissionBoundary {

    private final UserMissionGoalProgressPersistence userMissionGoalProgressPersistence;
    private final UserMissionProgressPersistence userMissionProgressPersistence;
    private final UserMissionGoalPersistenceMapper userMissionGoalPersistenceMapper;
    private final UserMissionPersistenceMapper userMissionPersistenceMapper;

    public UpdateUserMissionDb(UserMissionGoalProgressPersistence userMissionGoalProgressPersistence, UserMissionProgressPersistence userMissionProgressPersistence, UserMissionGoalPersistenceMapper userMissionGoalPersistenceMapper, UserMissionPersistenceMapper userMissionPersistenceMapper) {
        this.userMissionGoalProgressPersistence = userMissionGoalProgressPersistence;
        this.userMissionProgressPersistence = userMissionProgressPersistence;
        this.userMissionGoalPersistenceMapper = userMissionGoalPersistenceMapper;
        this.userMissionPersistenceMapper = userMissionPersistenceMapper;
    }

    @Override
    public UserMissionProgress updateMissionStatus(UserMissionProgress userMissionProgress, ProgressStatusEnum progressStatusEnum) {
        userMissionProgressPersistence.startUserMission(userMissionProgress.getId(), ProgressStatusEnumJPA.getProgressJPAByProgressStatusEnum(progressStatusEnum));
        return userMissionProgressPersistence
                .findById(userMissionProgress.getId())
                .map(userMissionPersistenceMapper::JpaEntityToModel)
                .orElse(null);
    }

    @Override
    public UserMissionProgress completeMission(UserMissionProgress userMissionProgress) {
        var id = userMissionProgressPersistence.completeMission(userMissionProgress.getId(), ProgressStatusEnumJPA.COMPLETED);
        return userMissionProgressPersistence.findById(id).
                map(userMissionPersistenceMapper::JpaEntityToModel)
                .orElse(null);
    }

    @Override
    public UserMissionGoalProgress updateGoalStatus(UserMissionGoalProgress userMissionGoalProgress, ProgressStatusEnum progressStatusEnum) {
        var id = userMissionGoalProgressPersistence.startUserMissionGoal(userMissionGoalProgress.getId(), ProgressStatusEnumJPA.getProgressJPAByProgressStatusEnum(progressStatusEnum));
        return userMissionGoalProgressPersistence
                .findById(userMissionGoalProgress.getId())
                .map(userMissionGoalPersistenceMapper::JpaEntityToModel)
                .orElse(null);
    }

    @Override
    public UserMissionGoalProgress completeGoal(UserMissionGoalProgress userMissionGoalProgress) {
        var id = userMissionGoalProgressPersistence.completeMissionGoal(userMissionGoalProgress.getId(), ProgressStatusEnumJPA.COMPLETED);
        return userMissionGoalProgressPersistence
                .findById(id)
                .map(userMissionGoalPersistenceMapper::JpaEntityToModel)
                .orElse(null);
    }
}

package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.FindUserMissionBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.entities.progress.enums.ProgressStatusEnumJPA;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.UserMissionProgressPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.UserJPA;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.MissionJPA;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.ProgressStatusEnum;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.UserMissionPersistenceMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindUserMissionDb implements FindUserMissionBoundary {

    private final UserMissionProgressPersistence userMissionProgressPersistence;
    private final UserMissionPersistenceMapper userMissionPersistenceMapper;

    public FindUserMissionDb(UserMissionProgressPersistence userMissionProgressPersistence, UserMissionPersistenceMapper userMissionPersistenceMapper) {
        this.userMissionProgressPersistence = userMissionProgressPersistence;
        this.userMissionPersistenceMapper = userMissionPersistenceMapper;
    }

    @Override
    public Optional<UserMissionProgress> byUserAndMissionAndStatus(User user, Mission mission, ProgressStatusEnum progressStatusEnum) {
        var userJPA = UserJPA.byIdentifier(user.getIdentifier());
        var missionJPA = MissionJPA.byIdentifier(mission.getIdentifier());
        return userMissionProgressPersistence
                .findByUserAndMissionAndStatus(userJPA, missionJPA, ProgressStatusEnumJPA.getProgressJPAByProgressStatusEnum(progressStatusEnum))
                .map(userMissionPersistenceMapper::JpaEntityToModel)
                .or(Optional::empty)
                ;
    }

}

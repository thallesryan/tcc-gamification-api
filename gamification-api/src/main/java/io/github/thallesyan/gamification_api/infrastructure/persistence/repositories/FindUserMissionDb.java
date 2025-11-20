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

import java.util.List;
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
    public Optional<UserMissionProgress> byUserIdentifierAndMissionAndStatus(User user, Mission mission, ProgressStatusEnum progressStatusEnum) {
        var userJPA = UserJPA.byIdentifier(user.getIdentifier());
        var missionJPA = MissionJPA.byIdentifier(mission.getIdentifier());
        return userMissionProgressPersistence
                .findByUserAndMissionAndStatus(userJPA, missionJPA, ProgressStatusEnumJPA.getProgressJPAByProgressStatusEnum(progressStatusEnum))
                .map(userMissionPersistenceMapper::JpaEntityToModel)
                .or(Optional::empty);
    }

    @Override
    public Optional<UserMissionProgress> byUserAndMission(User user, Mission mission) {
        var userJPA = UserJPA.byIdentifier(user.getIdentifier());
        var missionJPA = MissionJPA.byIdentifier(mission.getIdentifier());
        return userMissionProgressPersistence
                .findByUserAndMission(userJPA, missionJPA)
                .map(userMissionPersistenceMapper::JpaEntityToModel);
    }

    @Override
    public List<UserMissionProgress> byUserAndStatus(User user, ProgressStatusEnum progressStatusEnum) {
        var userJPA = UserJPA.byIdentifier(user.getIdentifier());
        return userMissionProgressPersistence
                .findUserMissionProgressJPAByUserAndStatus(userJPA, ProgressStatusEnumJPA.getProgressJPAByProgressStatusEnum(progressStatusEnum))
                .stream()
                .map(userMissionPersistenceMapper::JpaEntityToModel).toList();
    }

    @Override
    public Optional<UserMissionProgress> byId(Integer id) {
        return userMissionProgressPersistence
                .findById(id)
                .map(userMissionPersistenceMapper::JpaEntityToModel)
                .or(Optional::empty);
    }

}

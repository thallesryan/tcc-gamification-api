package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.FindMissionCompletionHistoryBoundary;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.domain.entities.progress.enums.ProgressStatusEnumJPA;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.UserMissionProgressPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.UserMissionPersistenceMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FindMissionCompletionHistoryDb implements FindMissionCompletionHistoryBoundary {

    private final UserMissionProgressPersistence userMissionProgressPersistence;
    private final UserMissionPersistenceMapper userMissionPersistenceMapper;

    public FindMissionCompletionHistoryDb(UserMissionProgressPersistence userMissionProgressPersistence,
                                          UserMissionPersistenceMapper userMissionPersistenceMapper) {
        this.userMissionProgressPersistence = userMissionProgressPersistence;
        this.userMissionPersistenceMapper = userMissionPersistenceMapper;
    }

    @Override
    public List<UserMissionProgress> findCompletedByMission(UUID missionId) {
        return userMissionProgressPersistence
                .findCompletedByMission(missionId, ProgressStatusEnumJPA.COMPLETED)
                .stream()
                .map(userMissionPersistenceMapper::JpaEntityToModel)
                .collect(Collectors.toList());
    }
}


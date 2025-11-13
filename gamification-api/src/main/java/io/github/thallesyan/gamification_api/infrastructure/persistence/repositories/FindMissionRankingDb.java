package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.FindMissionRankingBoundary;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.domain.entities.progress.enums.ProgressStatusEnumJPA;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.UserMissionProgressPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.UserMissionPersistenceMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FindMissionRankingDb implements FindMissionRankingBoundary {

    private final UserMissionProgressPersistence userMissionProgressPersistence;
    private final UserMissionPersistenceMapper userMissionPersistenceMapper;

    public FindMissionRankingDb(UserMissionProgressPersistence userMissionProgressPersistence, UserMissionPersistenceMapper userMissionPersistenceMapper) {
        this.userMissionProgressPersistence = userMissionProgressPersistence;
        this.userMissionPersistenceMapper = userMissionPersistenceMapper;
    }

    @Override
    public List<UserMissionProgress> findByMissionIdAndPlatform(UUID missionId, String platform) {
        return userMissionProgressPersistence
                .findByMissionIdAndPlatformOrderedByCompletionTime(missionId.toString(), platform, ProgressStatusEnumJPA.COMPLETED)
                .stream()
                .map(userMissionPersistenceMapper::JpaEntityToModel)
                .collect(Collectors.toList());
    }
}


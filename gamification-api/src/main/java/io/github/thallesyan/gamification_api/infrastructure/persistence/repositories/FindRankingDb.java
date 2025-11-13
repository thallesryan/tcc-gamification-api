package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.FindRankingBoundary;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserProgress;
import io.github.thallesyan.gamification_api.domain.entities.progress.enums.RankingType;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.UserProgressJpaPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.PlatformJPA;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.UserProgressJPA;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.UserProgressPersistenceMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindRankingDb implements FindRankingBoundary {

    private final UserProgressJpaPersistence userProgressJpaPersistence;
    private final UserProgressPersistenceMapper userProgressPersistenceMapper;

    public FindRankingDb(UserProgressJpaPersistence userProgressJpaPersistence, UserProgressPersistenceMapper userProgressPersistenceMapper) {
        this.userProgressJpaPersistence = userProgressJpaPersistence;
        this.userProgressPersistenceMapper = userProgressPersistenceMapper;
    }

    @Override
    public List<UserProgress> findByPlatform(String platform, RankingType rankingType) {
        PlatformJPA platformJPA = new PlatformJPA(platform);
        List<UserProgressJPA> results = switch (rankingType) {
            case POINTS -> userProgressJpaPersistence.findByPlatformOrderByPoints(platformJPA);
            case GOALS_COMPLETED -> userProgressJpaPersistence.findByPlatformOrderByGoalsCompleted(platformJPA);
            case MISSION_COMPLETED -> userProgressJpaPersistence.findByPlatformOrderByMissionsCompleted(platformJPA);
        };
        
        return results.stream()
                .map(userProgressPersistenceMapper::toUserProgress)
                .collect(Collectors.toList());
    }
}


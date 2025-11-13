package io.github.thallesyan.gamification_api.domain.boundary;

import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;

import java.util.List;
import java.util.UUID;

public interface FindMissionRankingBoundary {
    List<UserMissionProgress> findByMissionIdAndPlatform(UUID missionId, String platform);
}


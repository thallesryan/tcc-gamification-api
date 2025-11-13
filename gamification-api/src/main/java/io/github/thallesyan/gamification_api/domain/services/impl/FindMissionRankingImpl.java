package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.FindMissionRankingBoundary;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.domain.services.FindMissionRanking;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FindMissionRankingImpl implements FindMissionRanking {

    private final FindMissionRankingBoundary findMissionRankingBoundary;

    public FindMissionRankingImpl(FindMissionRankingBoundary findMissionRankingBoundary) {
        this.findMissionRankingBoundary = findMissionRankingBoundary;
    }

    @Override
    public List<UserMissionProgress> findByMissionIdAndPlatform(UUID missionId, String platform) {
        return findMissionRankingBoundary.findByMissionIdAndPlatform(missionId, platform);
    }
}


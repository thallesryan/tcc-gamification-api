package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.FindRankingBoundary;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserProgress;
import io.github.thallesyan.gamification_api.domain.entities.progress.enums.RankingType;
import io.github.thallesyan.gamification_api.domain.services.FindRanking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindRankingImpl implements FindRanking {

    private final FindRankingBoundary findRankingBoundary;

    public FindRankingImpl(FindRankingBoundary findRankingBoundary) {
        this.findRankingBoundary = findRankingBoundary;
    }

    @Override
    public List<UserProgress> findByPlatform(String platform, RankingType rankingType) {
        return findRankingBoundary.findByPlatform(platform, rankingType);
    }
}


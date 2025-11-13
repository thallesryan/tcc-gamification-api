package io.github.thallesyan.gamification_api.domain.boundary;

import io.github.thallesyan.gamification_api.domain.entities.progress.UserProgress;
import io.github.thallesyan.gamification_api.domain.entities.progress.enums.RankingType;

import java.util.List;

public interface FindRankingBoundary {
    List<UserProgress> findByPlatform(String platform, RankingType rankingType);
}


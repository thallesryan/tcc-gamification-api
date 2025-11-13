package io.github.thallesyan.gamification_api.domain.services;

import io.github.thallesyan.gamification_api.domain.entities.progress.UserProgress;
import io.github.thallesyan.gamification_api.domain.entities.progress.enums.RankingType;

import java.util.List;

public interface FindRanking {
    List<UserProgress> findByPlatform(String platform, RankingType rankingType);
}


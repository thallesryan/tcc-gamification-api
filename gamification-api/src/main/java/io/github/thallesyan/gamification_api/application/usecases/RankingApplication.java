package io.github.thallesyan.gamification_api.application.usecases;

import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserProgress;
import io.github.thallesyan.gamification_api.domain.entities.progress.enums.RankingType;
import io.github.thallesyan.gamification_api.domain.services.FindMissionRanking;
import io.github.thallesyan.gamification_api.domain.services.FindRanking;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class RankingApplication {

    private final FindRanking findRanking;
    private final FindMissionRanking findMissionRanking;

    public RankingApplication(FindRanking findRanking, FindMissionRanking findMissionRanking) {
        this.findRanking = findRanking;
        this.findMissionRanking = findMissionRanking;
    }

    public List<UserProgress> getRankingByPlatform(String platform, RankingType rankingType) {
        return findRanking.findByPlatform(platform, rankingType);
    }

    public List<UserMissionProgress> getMissionRankingByMissionIdAndPlatform(UUID missionId, String platform) {
        return findMissionRanking.findByMissionIdAndPlatform(missionId, platform);
    }
}


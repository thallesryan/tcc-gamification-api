package io.github.thallesyan.gamification_api.application.usecases;

import io.github.thallesyan.gamification_api.application.dtos.CompletionTimeDTO;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserProgress;
import io.github.thallesyan.gamification_api.domain.entities.progress.enums.RankingType;
import io.github.thallesyan.gamification_api.domain.services.FindMissionRanking;
import io.github.thallesyan.gamification_api.domain.services.FindRanking;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        return findMissionRanking.findByMissionIdAndPlatform(missionId, platform)
                .stream()
                .sorted(Comparator.comparingLong(this::calculateDurationMillis))
                .collect(Collectors.toList());
    }

    private long calculateDurationMillis(UserMissionProgress progress) {
        var startDate = progress.getStartDate();
        var completionDate = progress.getCompletionDate();

        if (startDate == null || completionDate == null) {
            return Long.MAX_VALUE;
        }

        return Duration.between(startDate, completionDate).toMillis();
    }

}


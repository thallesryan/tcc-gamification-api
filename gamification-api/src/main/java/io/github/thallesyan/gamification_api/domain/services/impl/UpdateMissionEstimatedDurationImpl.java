package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.FindMissionCompletionHistoryBoundary;
import io.github.thallesyan.gamification_api.domain.boundary.UpdateMissionEstimatedDurationBoundary;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.domain.services.UpdateMissionEstimatedDuration;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UpdateMissionEstimatedDurationImpl implements UpdateMissionEstimatedDuration {

    private final FindMissionCompletionHistoryBoundary completionHistoryBoundary;
    private final UpdateMissionEstimatedDurationBoundary updateMissionEstimatedDurationBoundary;

    public UpdateMissionEstimatedDurationImpl(FindMissionCompletionHistoryBoundary completionHistoryBoundary,
                                              UpdateMissionEstimatedDurationBoundary updateMissionEstimatedDurationBoundary) {
        this.completionHistoryBoundary = completionHistoryBoundary;
        this.updateMissionEstimatedDurationBoundary = updateMissionEstimatedDurationBoundary;
    }

    @Override
    public void recalculate(UUID missionId) {
        var completionHistory = completionHistoryBoundary.findCompletedByMission(missionId);

        var historyDurationTotalTotal = completionHistory.stream().map(userMissionProgress -> {
            return Duration.between(userMissionProgress.getStartDate(), userMissionProgress.getCompletionDate());
        }).reduce(Duration.ZERO, (a, b) -> a.plus(b));

        var durationAvg =  historyDurationTotalTotal.dividedBy(completionHistory.size());

        updateMissionEstimatedDurationBoundary.updateEstimatedDuration(missionId, durationAvg);
    }


}


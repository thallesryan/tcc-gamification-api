package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;


public class CalculateProgress {

    public static Double missionProgress(UserMissionProgress userMissionProgress) {
        var userGoals = userMissionProgress.getUserGoalsProgress();

        var goalsCompletedSize = userMissionProgress.getCountGoalsCompleted();
        var goalProgressSize = userGoals.size();

        if(goalProgressSize == 0 || goalsCompletedSize == 0) return 0.0;


        var eachCompletePercent = 100D/goalProgressSize;
        return eachCompletePercent * goalsCompletedSize;
    }
}

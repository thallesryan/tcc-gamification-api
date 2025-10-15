package io.github.thallesyan.gamification_api.domain.services;

import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionGoalProgress;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;

public interface UpdateUserMission {
    UserMissionProgress startMission(UserMissionProgress userMissionProgress);
    UserMissionProgress startGoal(UserMissionProgress userMissionProgress, UserMissionGoalProgress userMissionGoalProgress);
    UserMissionProgress completeGoal(UserMissionProgress userMissionProgress, UserMissionGoalProgress userMissionGoalProgress);
    UserMissionProgress completeMission(UserMissionProgress userMissionProgress);
}

package io.github.thallesyan.gamification_api.application.usecases;

import io.github.thallesyan.gamification_api.application.exceptions.UserNotFoundException;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserProgress;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserReward;
import io.github.thallesyan.gamification_api.domain.entities.reward.Badge;
import io.github.thallesyan.gamification_api.domain.services.FindUserProgress;
import io.github.thallesyan.gamification_api.domain.services.UpdateUserProgress;
import org.springframework.stereotype.Component;

@Component
public class UserProgressPoints {

    private final FindUserProgress findUserProgress;
    private final UpdateUserProgress updateUserProgress;

    public UserProgressPoints(FindUserProgress findUserProgress, UpdateUserProgress updateUserProgress) {
        this.findUserProgress = findUserProgress;
        this.updateUserProgress = updateUserProgress;
    }

    public UserProgress addMissionPoints(UserMissionProgress userMissionProgress) {
        var user = userMissionProgress.getUser();
        var userProgress = findUserProgress.findByUser(user).orElseThrow(UserNotFoundException::new);
        var mission = userMissionProgress.getMission();
        verifyLevelUpAndAddPoints(userProgress, mission.getPoints());
        userProgress.addMissionCompleted();
        return updateUserProgress.updateUserProgress(userProgress);
    }

    public void addRewardPoints(UserReward userReward) {
        if (userReward.getReward() != null && userReward.getReward().getPoints() != null) {
            var userProgress = findUserProgress.findByUser(userReward.getUser()).orElseThrow(UserNotFoundException::new);
            verifyLevelUpAndAddPoints(userProgress, userReward.getReward().getPoints());
            addBadgePoints(userProgress, userReward.getReward().getBadge());
            updateUserProgress.updateUserProgress(userProgress);
        }
    }

    private void addBadgePoints(UserProgress userProgress, Badge badgePoints) {
        if (badgePoints != null) {
            userProgress.setBadgesEarned(userProgress.getBadgesEarned() + 1);
            verifyLevelUpAndAddPoints(userProgress, badgePoints.getRarity().getPoints());
        }
    }

    private void verifyLevelUpAndAddPoints(UserProgress userProgress, Integer points) {
        Integer pointsToAdd = points;
        if (userProgress.isUserLevelUpping(points)) {
            pointsToAdd = userProgress.getSurplusPointsLevelUp(points);
            userProgress.redefineTotalPoints();
            userProgress.increaseLevel();
        }
        userProgress.addMissionPoints(pointsToAdd);
    }
}


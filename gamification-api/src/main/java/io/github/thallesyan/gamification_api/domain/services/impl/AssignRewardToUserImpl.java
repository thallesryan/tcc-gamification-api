package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.application.usecases.UserProgressPoints;
import io.github.thallesyan.gamification_api.domain.boundary.AssignRewardToUserBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Reward;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Rule;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserReward;
import io.github.thallesyan.gamification_api.domain.services.AssignRewardToUser;
import io.github.thallesyan.gamification_api.domain.services.FindUserMissionById;
import org.springframework.stereotype.Service;

@Service
public class AssignRewardToUserImpl implements AssignRewardToUser {

    private final AssignRewardToUserBoundary assignRewardToUserBoundary;
    private final UserProgressPoints userProgressPoints;
    private final FindUserMissionById findUserMissionById;

    public AssignRewardToUserImpl(AssignRewardToUserBoundary assignRewardToUserBoundary, UserProgressPoints userProgressPoints, FindUserMissionById findUserMissionById) {
        this.assignRewardToUserBoundary = assignRewardToUserBoundary;
        this.userProgressPoints = userProgressPoints;
        this.findUserMissionById = findUserMissionById;
    }

    @Override
    public void assignReward(Integer userMissionProgressId) {
        UserMissionProgress userMissionProgress = findUserMissionById.byId(userMissionProgressId);
        
        User user = userMissionProgress.getUser();
        Reward reward = userMissionProgress.getMission().getReward();
        Rule rule = userMissionProgress.getMission().getRule();

        if (reward == null || rule == null) {
            return;
        }

        if (userMissionProgress.isDurationValidToGainReward(rule)) {
            UserReward userReward = new UserReward(user, reward);
            var updatedUserReward = assignRewardToUserBoundary.assignReward(userReward);

            userProgressPoints.addRewardPoints(updatedUserReward);
        }
    }
}


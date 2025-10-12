package io.github.thallesyan.gamification_api.domain.entities.progress;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.ProgressStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMission {
    private Integer id;
    private Mission mission;
    private User user;
    private ProgressStatusEnum status;
    private Double progressPercentage;
    private Integer pointsEarned;
    private List<UserMissionGoal> userGoalsProgress;

    public UserMissionGoal getGoalByOrder(Integer order) {
        return userGoalsProgress.get(order - 1);
    }
}

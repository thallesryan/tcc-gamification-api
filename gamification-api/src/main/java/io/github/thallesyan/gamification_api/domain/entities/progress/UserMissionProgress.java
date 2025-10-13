package io.github.thallesyan.gamification_api.domain.entities.progress;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.ProgressStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMissionProgress extends BaseProgress{
    private Mission mission;
    private User user;
    private Double progressPercentage;
    private Integer pointsEarned;
    private List<UserMissionGoalProgress> userGoalsProgress;

    public UserMissionGoalProgress getGoalProgressByOrder(Integer order) {
        return userGoalsProgress.get(order - 1);
    }

    public UserMissionGoalProgress setGoalProgressByOrder(UserMissionGoalProgress userMissionGoalProgress, Integer order) {
        return userGoalsProgress.set(order - 1, userMissionGoalProgress);
    }
}

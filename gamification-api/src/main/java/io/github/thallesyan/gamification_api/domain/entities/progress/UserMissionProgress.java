package io.github.thallesyan.gamification_api.domain.entities.progress;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Rule;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.domain.services.impl.CalculateProgress;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.ProgressStatusEnum;
import lombok.*;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserMissionProgress extends BaseProgress{
    private Mission mission;
    private User user;
    private Double progressPercentage;
    private Integer pointsEarned;
    private List<UserMissionGoalProgress> userGoalsProgress;

    public Double getProgressPercentage() {
        return CalculateProgress.missionProgress(this);
    }

    public UserMissionGoalProgress getGoalProgressByOrder(Integer order) {
        return userGoalsProgress.get(order - 1);
    }

    public UserMissionGoalProgress setGoalProgressByOrder(UserMissionGoalProgress userMissionGoalProgress, Integer order) {
        return userGoalsProgress.set(order - 1, userMissionGoalProgress);
    }

    public Optional<UserMissionGoalProgress> getCurrentGoal() {
        return userGoalsProgress.stream()
                .filter(goal -> goal.getStatus() == ProgressStatusEnum.IN_PROGRESS)
                .findFirst();
    }

    public Optional<UserMissionGoalProgress> getNextGoal() {
        return userGoalsProgress.stream()
                .filter(goal -> goal.getStatus() == ProgressStatusEnum.ASSIGNED)
                .findFirst();
    }

    public Optional<UserMissionGoalProgress> getLastGoal() {
        var listCount = userGoalsProgress.size();
        return userGoalsProgress.stream().skip(listCount - 1).findFirst();
    }

    public Long getCountGoalsCompleted() {
        return userGoalsProgress.stream().filter(progress -> progress.getStatus() == ProgressStatusEnum.COMPLETED).count();
    }

    public Duration getCompletionDuration(){
        if(getStatus() == ProgressStatusEnum.COMPLETED){
            return Duration.between(getStartDate(), getCompletionDate());
        }
        return null;
    }

    public boolean isDurationValidToGainReward(Rule rule){
        return switch (getCompletionDuration().compareTo(rule.getDurationLimit())) {
            case -1, 0 -> true;
            default -> false;
        };
    }
}

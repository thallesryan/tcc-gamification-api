package io.github.thallesyan.gamification_api.domain.entities.progress;

import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProgress {
    private UUID identifier;
    private User user;
    private Integer totalPoints = 0;
    private Integer currentLevel = 1;
    private Integer badgesEarned = 0;
    private Integer missionsCompleted = 0;
    private Integer goalsCompleted = 0;

    public UserProgress(User user) {
        this.user = user;
        this.totalPoints = 0;
        this.currentLevel = 1;
        this.badgesEarned = 0;
        this.missionsCompleted = 0;
        this.goalsCompleted = 0;
    }

    public void addMissionPoints(){
        missionsCompleted++;
    }

    public Integer addMissionPoints(Integer points){
        this.totalPoints = totalPoints + points;
        return  totalPoints ;
    }

    public void addGoalCompleted(){
        goalsCompleted++;
    }
}


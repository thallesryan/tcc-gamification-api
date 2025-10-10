package io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress;

import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.GoalJPA;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.MissionJPA;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.UserJPA;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_mission_progress",
       uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "mission_id"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserMissionProgressJPA extends BaseProgressEntityJPA {

    private UserMissionProgressJPA(UserJPA user, MissionJPA mission, List<UserMissionGoalProgress> userGoalsProgress) {
        this.user = user;
        this.mission = mission;
        this.userGoalsProgress = userGoalsProgress;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserJPA user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private MissionJPA mission;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ProgressStatusEnum status = ProgressStatusEnum.ASSIGNED;
    
    @Column(name = "progress_percentage")
    private Double progressPercentage = 0.0;
    
    @Column(name = "points_earned")
    private Integer pointsEarned = 0;

    @OneToMany(mappedBy = "userMissionProgress", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<UserMissionGoalProgress> userGoalsProgress = new ArrayList<>();

    public static UserMissionProgressJPA byMission(UserJPA userJPA, MissionJPA missionJPA){
        var goalsProgress = missionJPA.getGoals().stream().map(GoalJPA::getIdentifier).map(UserMissionGoalProgress::byGoalIdentifier).toList();
        var missionProgress = new UserMissionProgressJPA(userJPA,  missionJPA, goalsProgress);
        goalsProgress.forEach(goalProgress -> {
            goalProgress.setUserMissionProgress(missionProgress);
        });
        return missionProgress;
    }
}

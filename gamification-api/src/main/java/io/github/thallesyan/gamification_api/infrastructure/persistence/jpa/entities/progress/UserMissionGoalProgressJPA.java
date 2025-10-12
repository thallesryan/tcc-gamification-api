package io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress;

import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.GoalJPA;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user_mission_goal_progress")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMissionGoalProgressJPA extends BaseProgressEntityJPA {

    private UserMissionGoalProgressJPA(Integer id) {
        super(id);
    }

    @ManyToOne
    @JoinColumn(name = "goal_identifier")
    private GoalJPA goal;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_mission_progress_id")
    private UserMissionProgressJPA userMissionProgress;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ProgressStatusEnum status = ProgressStatusEnum.ASSIGNED;

    public static UserMissionGoalProgressJPA byGoalIdentifier(String goalIdentifier) {
        var userMissionGoal =  new UserMissionGoalProgressJPA();
        userMissionGoal.setGoal(new GoalJPA(UUID.fromString(goalIdentifier)));
        return userMissionGoal;
    }

    public static UserMissionGoalProgressJPA byGoalIdentifier(UUID goalIdentifier) {
        var userMissionGoal =  new UserMissionGoalProgressJPA();
        userMissionGoal.setGoal(new GoalJPA(goalIdentifier));
        return userMissionGoal;
    }

    public static UserMissionGoalProgressJPA byId(Integer id) {
        return new UserMissionGoalProgressJPA(id);
    }

}

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
public class UserMissionGoalProgress extends BaseProgressEntityJPA {

    private UserMissionGoalProgress(UUID goalIdentifier) {
        this.goal = new GoalJPA(goalIdentifier);
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

    public static UserMissionGoalProgress byGoalIdentifier(String goalIdentifier) {
        return new UserMissionGoalProgress(UUID.fromString(goalIdentifier));
    }

    public static UserMissionGoalProgress byGoalIdentifier(UUID goalIdentifier) {
        return new UserMissionGoalProgress(goalIdentifier);
    }


}

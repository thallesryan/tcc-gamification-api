package io.github.thallesyan.gamification_api.domain.entities.progress;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Goal;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.ProgressStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMissionGoal {
    private Integer id;
    private Goal goal;
    private UserMission serMissionProgress;
    private ProgressStatusEnum status;
}

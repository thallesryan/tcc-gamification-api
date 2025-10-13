package io.github.thallesyan.gamification_api.domain.entities.progress;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Goal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMissionGoalProgress extends BaseProgress{
    private Goal goal;
}

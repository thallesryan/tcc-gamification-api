package io.github.thallesyan.gamification_api.infrastructure.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NextGoalResponseDTO {
    UserGoalProgressResponseDTO currentGoal;
    UserGoalProgressResponseDTO nextGoal;
    UserGoalProgressResponseDTO lastGoal;
}

package io.github.thallesyan.gamification_api.infrastructure.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResolveGoalRequestDTO {
    @NotNull
    private Integer goalId;
}

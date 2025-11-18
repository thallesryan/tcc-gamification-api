package io.github.thallesyan.gamification_api.infrastructure.web.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissionCreationRequestDTO {

    @NotEmpty(message = "Title cant be empty or null")
    private String title;
    @NotEmpty(message = "Description cant be empty or null")
    private String description;
    private Integer difficultyLevel;
    private Integer points;
    @NotEmpty
    private List<GoalDTO> goals;
    @Valid
    private RuleDTO rule;
    @Valid
    private RewardAssociationRequestDTO reward;
}

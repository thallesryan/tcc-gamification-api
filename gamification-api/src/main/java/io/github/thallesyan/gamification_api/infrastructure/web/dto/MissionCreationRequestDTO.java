package io.github.thallesyan.gamification_api.infrastructure.web.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissionCreationRequestDTO {

    @NotNull(message = "Title cannot be null")
    @NotBlank(message = "Title cannot be blank")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;
    
    @NotNull(message = "Description cannot be null")
    @NotBlank(message = "Description cannot be blank")
    @Size(max = 1000, message = "Description must have at most 1000 characters")
    private String description;
    
    @Min(value = 1, message = "Difficulty level must be at least 1")
    private Integer difficultyLevel;
    
    @Min(value = 0, message = "Points must be at least 0")
    private Integer points;
    
    @NotNull(message = "Goals list cannot be null")
    @NotEmpty(message = "Goals list cannot be empty")
    @Valid
    private List<GoalDTO> goals;
    
    @Valid
    private RuleDTO rule;
    
    @Valid
    private RewardAssociationRequestDTO reward;
}

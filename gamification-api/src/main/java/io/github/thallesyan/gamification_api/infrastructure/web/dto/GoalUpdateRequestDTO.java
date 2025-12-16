package io.github.thallesyan.gamification_api.infrastructure.web.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoalUpdateRequestDTO {
    
    @Size(min = 1, max = 255, message = "Goal title must be between 1 and 255 characters")
    private String title;
    
    @Size(max = 1000, message = "Goal description must have at most 1000 characters")
    private String description;
}


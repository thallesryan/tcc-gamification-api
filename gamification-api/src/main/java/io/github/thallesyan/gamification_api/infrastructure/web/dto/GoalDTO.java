package io.github.thallesyan.gamification_api.infrastructure.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoalDTO {
    private UUID identifier;
    
    @NotNull(message = "Goal title cannot be null")
    @NotBlank(message = "Goal title cannot be blank")
    @Size(min = 1, max = 255, message = "Goal title must be between 1 and 255 characters")
    private String title;
    
    @Size(max = 1000, message = "Goal description must have at most 1000 characters")
    private String description;
    
    @Min(value = 0, message = "Goal order must be at least 0")
    private Integer order;
}

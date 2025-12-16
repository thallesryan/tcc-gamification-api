package io.github.thallesyan.gamification_api.infrastructure.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RewardUpdateRequestDTO {

    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    private String name;
    
    @Size(max = 1000, message = "Description must have at most 1000 characters")
    private String description;
    
    @Min(value = 0, message = "Points must be at least 0")
    private Integer points;
    
    private Boolean isActive;
}


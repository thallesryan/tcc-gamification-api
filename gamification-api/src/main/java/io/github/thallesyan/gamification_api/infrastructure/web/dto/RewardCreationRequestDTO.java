package io.github.thallesyan.gamification_api.infrastructure.web.dto;

import jakarta.validation.Valid;
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
public class RewardCreationRequestDTO {
    @Size(max = 255, message = "Identifier must have at most 255 characters")
    private String identifier;
    
    @NotNull(message = "Reward name cannot be null")
    @NotBlank(message = "Reward name cannot be blank")
    @Size(min = 1, max = 255, message = "Reward name must be between 1 and 255 characters")
    private String name;
    
    @Size(max = 1000, message = "Reward description must have at most 1000 characters")
    private String description;
    
    @NotNull(message = "Points cannot be null")
    @Min(value = 0, message = "Points must be at least 0")
    private Integer points;

    @Valid
    private BadgeAssociationRequestDTO badge;
}


package io.github.thallesyan.gamification_api.infrastructure.web.dto;

import io.github.thallesyan.gamification_api.domain.entities.reward.RarityEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RarityPointsRequestDTO {
    @NotNull(message = "Rarity value cannot be null")
    private RarityEnum value;
    
    @NotNull(message = "Points cannot be null")
    @Min(value = 0, message = "Points must be at least 0")
    private Integer points;
}

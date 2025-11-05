package io.github.thallesyan.gamification_api.infrastructure.web.dto.response;

import io.github.thallesyan.gamification_api.domain.entities.reward.RarityEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RarityResponseDTO {
    private RarityEnum value;
    private Double points;
}

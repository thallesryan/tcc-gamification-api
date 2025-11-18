package io.github.thallesyan.gamification_api.infrastructure.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LevelPointsResponseDTO {
    private Integer level;
    private Integer pointsRequired;
    private String platform;
}


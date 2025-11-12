package io.github.thallesyan.gamification_api.infrastructure.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RewardResponseDTO {
    private UUID identifier;
    private String name;
    private String description;
    private Integer points;
    private BadgeResponseDTO badge;
}


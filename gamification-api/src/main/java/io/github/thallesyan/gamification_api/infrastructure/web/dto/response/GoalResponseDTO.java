package io.github.thallesyan.gamification_api.infrastructure.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoalResponseDTO {
    private UUID identifier;
    private String title;
    private String description;
    private Integer order;
}


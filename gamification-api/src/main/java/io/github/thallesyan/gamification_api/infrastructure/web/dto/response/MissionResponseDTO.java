package io.github.thallesyan.gamification_api.infrastructure.web.dto.response;

import io.github.thallesyan.gamification_api.infrastructure.web.dto.GoalDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissionResponseDTO {
    private UUID identifier;
    private String title;
    private String description;
    private Integer difficultyLevel;
    private Integer points;
    private List<GoalDTO> goals;
}

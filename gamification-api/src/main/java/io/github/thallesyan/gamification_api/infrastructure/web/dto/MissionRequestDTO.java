package io.github.thallesyan.gamification_api.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissionRequestDTO {
    private String title;
    private String description;
    private Integer difficultyLevel;
    private List<GoalDTO> goals;
}

package io.github.thallesyan.gamification_api.infrastructure.web.dto.response;

import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.ProgressStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MissionStartResponseDTO {
    private String title;
    private String description;
    private List<UserGoalProgressResponseDTO> userGoalsProgress;
    private ProgressStatusEnum status;
    private LocalDateTime start;
    private LocalDateTime end;
}

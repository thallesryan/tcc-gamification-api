package io.github.thallesyan.gamification_api.infrastructure.web.dto.response;

import io.github.thallesyan.gamification_api.infrastructure.web.dto.enums.ProgressStatusDTOEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserGoalProgressResponseDTO {
    private String title;
    private String description;
    private ProgressStatusDTOEnum status;
    private LocalDateTime startTime;
}

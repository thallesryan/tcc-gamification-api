package io.github.thallesyan.gamification_api.domain.entities.progress;

import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.ProgressStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseProgress {
    private Integer id;
    private ProgressStatusEnum status;
    private LocalDateTime startDate;
    private LocalDateTime completionDate;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
}

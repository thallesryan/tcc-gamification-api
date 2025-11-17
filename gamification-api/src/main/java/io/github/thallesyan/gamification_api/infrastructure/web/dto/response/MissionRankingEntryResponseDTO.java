package io.github.thallesyan.gamification_api.infrastructure.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissionRankingEntryResponseDTO {
    private Integer position;
    private UUID userId;
    private String userName;
    private String userEmail;
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;
    private String completionTime;
}


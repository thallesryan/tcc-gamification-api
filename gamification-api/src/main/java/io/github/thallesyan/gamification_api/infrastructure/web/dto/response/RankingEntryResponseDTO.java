package io.github.thallesyan.gamification_api.infrastructure.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RankingEntryResponseDTO {
    private Integer position;
    private UUID userId;
    private String userName;
    private String userEmail;
    private Integer value;
}


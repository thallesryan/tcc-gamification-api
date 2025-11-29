package io.github.thallesyan.gamification_api.infrastructure.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRewardResponseDTO {
    private UUID identifier;
    private RewardResponseDTO reward;
    private String status;
    private LocalDateTime earnedAt;
    private Integer pointsValue;
}



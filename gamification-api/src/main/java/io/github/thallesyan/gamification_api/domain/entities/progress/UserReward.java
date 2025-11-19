package io.github.thallesyan.gamification_api.domain.entities.progress;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Reward;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReward {
    private UUID identifier;
    private User user;
    private Reward reward;
    private UserRewardStatus status = UserRewardStatus.EARNED;
    private LocalDateTime earnedAt;
    private LocalDateTime expiresAt;
    private Integer pointsValue;

    public UserReward(User user, Reward reward) {
        this.user = user;
        this.reward = reward;
        this.earnedAt = LocalDateTime.now();
        this.pointsValue = reward != null ? reward.getPoints() : null;
    }

    public enum UserRewardStatus {
        EARNED,
        EXPIRED,
        CANCELLED
    }
}


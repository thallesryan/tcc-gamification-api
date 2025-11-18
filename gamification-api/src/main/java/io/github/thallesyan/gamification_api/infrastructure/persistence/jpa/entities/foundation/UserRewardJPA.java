package io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "user_rewards")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserRewardJPA extends BaseEntityJPA {
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserJPA user;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reward_id", nullable = false)
    private RewardJPA reward;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private UserRewardStatus status = UserRewardStatus.EARNED;
    
    @Column(name = "earned_at", nullable = false)
    private java.time.LocalDateTime earnedAt;
    
    @Column(name = "expires_at")
    private java.time.LocalDateTime expiresAt;
    
    @Column(name = "points_value")
    private Integer pointsValue;
    
    public enum UserRewardStatus {
        EARNED,
        EXPIRED,
        CANCELLED
    }
}

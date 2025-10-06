package io.github.thallesyan.gamification_api.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "rewards")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RewardJPA extends BaseEntityJPA {
    
    @Column(name = "points", nullable = false)
    private Integer points;
    
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "reward_type", nullable = false)
    private RewardType rewardType;
    
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
    
    @Column(name = "expiration_days")
    private Integer expirationDays;
    
    @Column(name = "max_uses_per_user")
    private Integer maxUsesPerUser;
    
    public enum RewardType {
        POINTS,
        BADGE,
        ACHIEVEMENT,
        CUSTOM
    }
}

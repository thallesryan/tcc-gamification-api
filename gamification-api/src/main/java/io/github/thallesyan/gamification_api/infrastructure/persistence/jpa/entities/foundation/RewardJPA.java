package io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation;

import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.reward.BadgeJPA;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "rewards")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RewardJPA extends BaseEntityJPA {
    
    public RewardJPA(UUID identifier) {
        super(identifier);
    }
    
    @Column(name = "points", nullable = false)
    private Integer points;
    
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
//    @Enumerated(EnumType.STRING)
//    @Column(name = "reward_type", nullable = false)
//    private RewardType rewardType;
    
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
    
    @Column(name = "expiration_days")
    private Integer expirationDays;
    
    @Column(name = "max_uses_per_user")
    private Integer maxUsesPerUser;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "badge_id")
    private BadgeJPA badge;
    
    public enum RewardType {
        POINTS,
        BADGE,
        ACHIEVEMENT,
        CUSTOM
    }

    @PrePersist
    void prePersist() {
        isActive = true;
    }
    
    public static RewardJPA byIdentifier(String identifier) {
        return new RewardJPA(UUID.fromString(identifier));
    }

    public static RewardJPA byIdentifier(UUID identifier) {
        return new RewardJPA(identifier);
    }
}

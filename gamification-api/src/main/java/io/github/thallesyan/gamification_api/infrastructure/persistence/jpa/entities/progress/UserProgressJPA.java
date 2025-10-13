package io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress;

import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.BaseEntityJPA;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.UserJPA;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "user_progress")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserProgressJPA extends BaseEntityJPA {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserJPA user;

    private String platform;
    
    @Column(name = "total_points", nullable = false)
    private Integer totalPoints = 0;
    
    @Column(name = "current_level", nullable = false)
    private Integer currentLevel = 1;
    
    @Column(name = "experience_points", nullable = false)
    private Integer experiencePoints = 0;
    
    @Column(name = "badges_earned")
    private Integer badgesEarned = 0;
    
    @Column(name = "missions_completed")
    private Integer missionsCompleted = 0;
    
    @Column(name = "goals_completed")
    private Integer goalsCompleted = 0;
    
    @Column(name = "last_activity_at")
    private java.time.LocalDateTime lastActivityAt;

}

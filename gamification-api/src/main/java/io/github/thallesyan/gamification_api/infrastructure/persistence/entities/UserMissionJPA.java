package io.github.thallesyan.gamification_api.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "user_missions",
       uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "mission_id"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserMissionJPA extends BaseEntityJPA {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserJPA user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private MissionJPA mission;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private UserMissionStatus status = UserMissionStatus.ASSIGNED;
    
    @Column(name = "progress_percentage")
    private Double progressPercentage = 0.0;
    
    @Column(name = "started_at")
    private java.time.LocalDateTime startedAt;
    
    @Column(name = "completed_at")
    private java.time.LocalDateTime completedAt;
    
    @Column(name = "points_earned")
    private Integer pointsEarned = 0;
    
    public enum UserMissionStatus {
        ASSIGNED,
        IN_PROGRESS,
        COMPLETED,
        FAILED,
        ABANDONED
    }
}

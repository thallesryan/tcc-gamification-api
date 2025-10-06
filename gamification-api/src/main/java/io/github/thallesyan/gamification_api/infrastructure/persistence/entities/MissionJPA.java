package io.github.thallesyan.gamification_api.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "missions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MissionJPA extends BaseInformationJPA {
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MissionStatus status = MissionStatus.ACTIVE;
    
    @Column(name = "difficulty_level")
    private Integer difficultyLevel = 1;
    
    @Column(name = "estimated_duration_hours")
    private Integer estimatedDurationHours;
    
    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GoalJPA> goals = new ArrayList<>();
    
    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RuleJPA> rules = new ArrayList<>();
    
    public enum MissionStatus {
        ACTIVE,
        COMPLETED,
        PAUSED,
        CANCELLED,
        EXPIRED
    }
}

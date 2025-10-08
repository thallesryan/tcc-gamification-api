package io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation;

import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.RuleJPA;
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

    @Column(name = "difficulty_level")
    private Integer difficultyLevel = 1;
    
    @Column(name = "estimated_duration_hours")
    private Integer estimatedDurationHours;
    
    @OneToMany(mappedBy = "mission", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<GoalJPA> goals = new ArrayList<>();
    
    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RuleJPA> rules = new ArrayList<>();
}

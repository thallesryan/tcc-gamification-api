package io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Goal;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.RuleJPA;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "missions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MissionJPA extends BaseInformationJPA {

    public MissionJPA(UUID identifier) {
        super(identifier);
    }

    @Column(name = "difficulty_level")
    private Integer difficultyLevel = 1;
    
    @Column(name = "estimated_duration_hours")
    private Integer estimatedDurationHours;
    
    @OneToMany(mappedBy = "mission", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<GoalJPA> goals = new ArrayList<>();
    
    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RuleJPA> rules = new ArrayList<>();

    public static MissionJPA byMissionAndGoalsIdentifier(Mission mission) {
        var missionJPA = new MissionJPA(mission.getIdentifier());
        missionJPA.setGoals(mission.getGoals().stream().map(Goal::getIdentifier).map(GoalJPA::new).toList());
        return missionJPA;
    }

    public static MissionJPA byIdentifier(String identifier){
        return new MissionJPA(UUID.fromString(identifier));
    }

    public static MissionJPA byIdentifier(UUID identifier){
        return new MissionJPA(identifier);
    }
}

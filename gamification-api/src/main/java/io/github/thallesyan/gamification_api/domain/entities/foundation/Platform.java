package io.github.thallesyan.gamification_api.domain.entities.foundation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Platform {
    private String name;
    private Integer progressBasePoints;
    private Double progressFormula;
    
    public Platform(String name) {
        this.name = name;
        this.progressBasePoints = null;
        this.progressFormula = null;
    }

    public Integer calculateLevelPoints(int level){
        if(level == 1) return progressBasePoints;
        Double points = Double.valueOf(progressBasePoints);

        for(int i = level-1; i > 0; i--){
            points *= (1 + progressFormula);
        }

        return points.intValue();
    }
}

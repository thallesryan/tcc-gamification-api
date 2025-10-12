package io.github.thallesyan.gamification_api.infrastructure.web.dto;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Period;

@Data
public class MissionConclusionResponseDTO {
    private Period timeTakenToConclude;

    public void setTimeTakenToConclude(Period timeTakenToConclude) {

    }

    private Period calTimeTakenToConclude(Mission mission) {

        return null;
    }
}

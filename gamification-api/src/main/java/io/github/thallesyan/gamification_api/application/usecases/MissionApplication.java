package io.github.thallesyan.gamification_api.application.usecases;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.services.CreateMission;
import org.springframework.stereotype.Component;

@Component
public class MissionApplication {

    private final CreateMission createMission;

    public MissionApplication(CreateMission createMission) {
        this.createMission = createMission;
    }

    public Mission createMission(Mission mission) {
        return createMission.createMission(mission);
    }
}

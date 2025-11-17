package io.github.thallesyan.gamification_api.application.usecases;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.services.CreateMission;
import io.github.thallesyan.gamification_api.domain.services.FindMission;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class MissionApplication {

    private final CreateMission createMission;
    private final FindMission findMission;

    public MissionApplication(CreateMission createMission, FindMission findMission) {
        this.createMission = createMission;
        this.findMission = findMission;
    }

    public Mission createMission(Mission mission) {
        return createMission.createMission(mission);
    }

    public Optional<Mission> findById(UUID missionId) {
        return findMission.byMissionId(missionId);
    }
}

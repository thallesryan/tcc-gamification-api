package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.UpdateMissionEstimatedDurationBoundary;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.MissionJpaPersistence;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

@Service
public class UpdateMissionEstimatedDurationDb implements UpdateMissionEstimatedDurationBoundary {

    private final MissionJpaPersistence missionJpaPersistence;

    public UpdateMissionEstimatedDurationDb(MissionJpaPersistence missionJpaPersistence) {
        this.missionJpaPersistence = missionJpaPersistence;
    }

    @Override
    public void updateEstimatedDuration(UUID missionId, Duration estimatedDuration) {
        missionJpaPersistence.findById(missionId).ifPresent(mission -> {
            mission.setEstimatedDuration(estimatedDuration.toString());
            missionJpaPersistence.save(mission);
        });
    }
}


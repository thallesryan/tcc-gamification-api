package io.github.thallesyan.gamification_api.domain.boundary;

import java.time.Duration;
import java.util.UUID;

public interface UpdateMissionEstimatedDurationBoundary {
    void updateEstimatedDuration(UUID missionId, Duration estimatedDuration);
}


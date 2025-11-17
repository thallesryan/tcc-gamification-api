package io.github.thallesyan.gamification_api.domain.services;

import java.time.LocalDateTime;
import java.util.UUID;

public interface UpdateMissionEstimatedDuration {
    void recalculate(UUID missionId);
}


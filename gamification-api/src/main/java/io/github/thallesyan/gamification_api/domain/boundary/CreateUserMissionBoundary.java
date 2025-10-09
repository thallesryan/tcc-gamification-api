package io.github.thallesyan.gamification_api.domain.boundary;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;

import java.util.List;

public interface CreateUserMissionBoundary {
    void createUserMissionProgress(User user, List<Mission> missions);
}

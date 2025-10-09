package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.CreateUserMissionBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.domain.services.BindUserMissions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BindMissionsImpl implements BindUserMissions {

    private final CreateUserMissionBoundary createUserMissionBoundary;

    public BindMissionsImpl(CreateUserMissionBoundary createUserMissionBoundary) {
        this.createUserMissionBoundary = createUserMissionBoundary;
    }

    @Override
    public void bindMissions(User user, List<Mission> missions) {
        createUserMissionBoundary.createUserMissionProgress(user, missions);
    }
}

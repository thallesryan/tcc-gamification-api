package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.application.usecases.UserMissionValidation;
import io.github.thallesyan.gamification_api.domain.boundary.CreateUserMissionBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.domain.services.BindUserMissions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BindMissionsImpl implements BindUserMissions {

    private final CreateUserMissionBoundary createUserMissionBoundary;
    private final UserMissionValidation userMissionValidation;

    public BindMissionsImpl(CreateUserMissionBoundary createUserMissionBoundary, UserMissionValidation userMissionValidation) {
        this.createUserMissionBoundary = createUserMissionBoundary;
        this.userMissionValidation = userMissionValidation;
    }

    @Override
    public List<Mission> bindMissions(User user, List<Mission> missions) {
        var missionsAlreadyExisted = userMissionValidation.validateUserMissionProgressExists(user, missions);
        missions.removeAll(missionsAlreadyExisted);
        if(!missions.isEmpty()) createUserMissionBoundary.createUserMissionProgress(user, missions);

        return missionsAlreadyExisted;
    }
}

package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.application.exceptions.UserMissionNotFound;
import io.github.thallesyan.gamification_api.domain.boundary.FindUserMissionBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMission;
import io.github.thallesyan.gamification_api.domain.services.FindUserMission;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.ProgressStatusEnum;
import org.springframework.stereotype.Service;

@Service
public class FindUserMissionByUserIdentifierImpl implements FindUserMission {

    private final FindUserMissionBoundary boundary;

    public FindUserMissionByUserIdentifierImpl(FindUserMissionBoundary boundary) {
        this.boundary = boundary;
    }

    @Override
    public UserMission byMissionIdAndStatus(String userIdentifier, String missionIdentifier, ProgressStatusEnum progressStatusEnum) {
        //todo mudar exececao/adaptar
        return boundary
                .byUserAndMissionAndStatus(User.byIdentifier(userIdentifier), Mission.byIdentifier(missionIdentifier), progressStatusEnum)
                .orElseThrow(() -> UserMissionNotFound.missionToStartNotFound(userIdentifier, missionIdentifier));
    }

}

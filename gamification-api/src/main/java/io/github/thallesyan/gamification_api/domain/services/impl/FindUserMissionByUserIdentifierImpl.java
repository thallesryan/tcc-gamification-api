package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.application.exceptions.UserMissionNotFound;
import io.github.thallesyan.gamification_api.domain.boundary.FindUserMissionBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.domain.services.FindUserMission;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.ProgressStatusEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("findUserMissionByUserIdentifierImpl")
public class FindUserMissionByUserIdentifierImpl implements FindUserMission {

    private final FindUserMissionBoundary boundary;

    public FindUserMissionByUserIdentifierImpl(FindUserMissionBoundary boundary) {
        this.boundary = boundary;
    }

    @Override
    public UserMissionProgress byMissionIdAndStatus(String userIdentifier, String platform, String missionIdentifier, ProgressStatusEnum progressStatusEnum) {
        //todo mudar exececao/adaptar
        return boundary
                .byUserIdentifierAndMissionAndStatus(User.byIdentifier(userIdentifier), Mission.byIdentifier(missionIdentifier), progressStatusEnum)
                .orElseThrow(() -> UserMissionNotFound.missionToStartNotFound(userIdentifier, missionIdentifier));
    }

}

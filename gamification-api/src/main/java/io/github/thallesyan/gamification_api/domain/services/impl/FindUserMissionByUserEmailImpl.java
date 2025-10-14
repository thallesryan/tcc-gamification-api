package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.application.exceptions.UserMissionNotFound;
import io.github.thallesyan.gamification_api.application.exceptions.UserNotFoundException;
import io.github.thallesyan.gamification_api.domain.boundary.FindUserBoundary;
import io.github.thallesyan.gamification_api.domain.boundary.FindUserMissionBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.domain.services.FindUserMission;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.ProgressStatusEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
@Qualifier("findUserMissionByUserEmailImpl")
public class FindUserMissionByUserEmailImpl implements FindUserMission {

    private final FindUserBoundary  findUserBoundary;
    private final FindUserMissionBoundary boundary;

    public FindUserMissionByUserEmailImpl(FindUserBoundary findUserBoundary, FindUserMissionBoundary boundary) {
        this.findUserBoundary = findUserBoundary;
        this.boundary = boundary;
    }

    @Override
    public UserMissionProgress byMissionIdAndStatus(String userEmail, String missionIdentifier, ProgressStatusEnum progressStatusEnum) {
        var user = findUserBoundary.ByEmail(userEmail).orElseThrow(UserNotFoundException::new);
        return boundary
                .byUserIdentifierAndMissionAndStatus(user, Mission.byIdentifier(missionIdentifier), progressStatusEnum)
                .orElseThrow(() -> UserMissionNotFound.missionToStartNotFound(user.getEmail(), missionIdentifier));
    }

}


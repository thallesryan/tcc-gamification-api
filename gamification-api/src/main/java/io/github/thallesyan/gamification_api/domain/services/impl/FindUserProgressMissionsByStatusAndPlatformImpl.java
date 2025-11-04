package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.application.exceptions.UserNotFoundException;
import io.github.thallesyan.gamification_api.domain.boundary.FindUserMissionBoundary;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.domain.services.FindUserByEmail;
import io.github.thallesyan.gamification_api.domain.services.FindUserProgressMissionsByStatusAndPlatform;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.ProgressStatusEnum;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindUserProgressMissionsByStatusAndPlatformImpl implements FindUserProgressMissionsByStatusAndPlatform {
    private final FindUserMissionBoundary findUserMissionBoundary;
    private final FindUserByEmail findUserByEmail;

    public FindUserProgressMissionsByStatusAndPlatformImpl(FindUserMissionBoundary findUserMissionBoundary, FindUserByEmail findUserByEmail) {
        this.findUserMissionBoundary = findUserMissionBoundary;
        this.findUserByEmail = findUserByEmail;
    }

    @Override
    public List<UserMissionProgress> byUserIdentifier(String userIdentifier, String platform, ProgressStatusEnum status) {
        return List.of();
    }

    @Override
    public List<UserMissionProgress> byUserEmail(String userEmail, String platform, ProgressStatusEnum status) {
        var user = findUserByEmail.byEmail(userEmail, platform).orElseThrow(UserNotFoundException::new);
        return findUserMissionBoundary.byUserAndStatus(user, status);
    }
}

package io.github.thallesyan.gamification_api.domain.services;

import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionGoalProgress;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.ProgressStatusEnum;

import java.util.List;

public interface FindUserProgressMissionsByStatusAndPlatform {

    List<UserMissionProgress> byUserIdentifier(String userIdentifier, String platform, ProgressStatusEnum status);
    List<UserMissionProgress> byUserEmail(String userEmail,  String platform, ProgressStatusEnum status);
}

package io.github.thallesyan.gamification_api.application.exceptions;

import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.ProgressStatusEnum;

public class UserMissionNotFound extends RuntimeException{
    private static final String MISSION_READY_TO_START_NOT_FOUND = "User with %s %s does not has mission with id %s ready to start";

    private UserMissionNotFound(String message) {
        super(message);
    }

    public static UserMissionNotFound missionToStartNotFound(String identifier, String missionIdentifier) {
        return new UserMissionNotFound(
                String.format(MISSION_READY_TO_START_NOT_FOUND, "Identifier", identifier, missionIdentifier));
    }
}

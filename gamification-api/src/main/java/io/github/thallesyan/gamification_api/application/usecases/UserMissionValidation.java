package io.github.thallesyan.gamification_api.application.usecases;

import io.github.thallesyan.gamification_api.domain.boundary.FindUserMissionBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.ProgressStatusEnum;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserMissionValidation {

    private final FindUserMissionBoundary findUserMissionBoundary;

    public UserMissionValidation(FindUserMissionBoundary findUserMissionBoundary) {
        this.findUserMissionBoundary = findUserMissionBoundary;
    }

    public List<Mission> validateUserMissionProgressExists(User user, List<Mission> missions) {
        List<Mission> missionsWithProgress = new ArrayList<>();
        
        for (Mission mission : missions) {
            Optional<UserMissionProgress> existingProgress = findUserMissionBoundary
                    .byUserAndMission(user, mission);
            
            if (existingProgress.isPresent()) {
                missionsWithProgress.add(mission);
            }
        }
        
        return missionsWithProgress;
    }
}


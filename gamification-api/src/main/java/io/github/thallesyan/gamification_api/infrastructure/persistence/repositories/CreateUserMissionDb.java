package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.CreateUserMissionBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.UserMissionProgressPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.UserJPA;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.MissionJPA;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.UserMissionProgressJPA;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateUserMissionDb implements CreateUserMissionBoundary {

    private final UserMissionProgressPersistence userMissionProgressPersistence;

    public CreateUserMissionDb(UserMissionProgressPersistence userMissionProgressPersistence) {
        this.userMissionProgressPersistence = userMissionProgressPersistence;
    }

    //todo criar entidade de retorno do salvamento do do usuario e missoes
    @Override
    public void createUserMissionProgress(User user, List<Mission> missions) {
        var userJPA = UserJPA.entityByIdentifier(user.getIdentifier());
        var userMissions = missions
                .stream()
                .map(MissionJPA::byMissionAndGoalsIdentifier)
                .map(mJPA -> UserMissionProgressJPA.byMission(userJPA, mJPA))
                .toList();

       userMissionProgressPersistence.saveAll(userMissions);
    }
}

package io.github.thallesyan.gamification_api.application.usecases;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Reward;
import io.github.thallesyan.gamification_api.domain.services.CreateMission;
import io.github.thallesyan.gamification_api.domain.services.FindMission;
import io.github.thallesyan.gamification_api.domain.services.FindRewards;
import io.github.thallesyan.gamification_api.domain.services.UpdateMission;
import io.github.thallesyan.gamification_api.application.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class MissionApplication {

    private final CreateMission createMission;
    private final FindMission findMission;
    private final FindRewards findRewards;
    private final UpdateMission updateMission;

    public MissionApplication(CreateMission createMission,
                              FindMission findMission,
                              FindRewards findRewards,
                              UpdateMission updateMission) {
        this.createMission = createMission;
        this.findMission = findMission;
        this.findRewards = findRewards;
        this.updateMission = updateMission;
    }

    public Mission createMission(Mission mission) {
        validateRewardExists(mission.getReward());
        return createMission.createMission(mission);
    }

    public Optional<Mission> findById(UUID missionId) {
        return findMission.byMissionId(missionId);
    }

    public Mission updateMission(UUID missionId, Mission mission, Integer difficultyLevel) {
        return updateMission.updateMission(missionId, mission, difficultyLevel);
    }

    private void validateRewardExists(Reward reward) {
        if (reward == null || reward.getIdentifier() == null) {
            return;
        }

        findRewards.findRewardById(reward.getIdentifier())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Reward",
                        "identifier",
                        reward.getIdentifier().toString()
                ));
    }
}

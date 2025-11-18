package io.github.thallesyan.gamification_api.application.usecases;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Reward;
import io.github.thallesyan.gamification_api.domain.services.CreateMission;
import io.github.thallesyan.gamification_api.domain.services.FindMission;
import io.github.thallesyan.gamification_api.domain.services.FindRewards;
import io.github.thallesyan.gamification_api.application.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class MissionApplication {

    private final CreateMission createMission;
    private final FindMission findMission;
    private final FindRewards findRewards;

    public MissionApplication(CreateMission createMission,
                              FindMission findMission,
                              FindRewards findRewards) {
        this.createMission = createMission;
        this.findMission = findMission;
        this.findRewards = findRewards;
    }

    public Mission createMission(Mission mission) {
        validateRewardExists(mission.getReward());
        return createMission.createMission(mission);
    }

    public Optional<Mission> findById(UUID missionId) {
        return findMission.byMissionId(missionId);
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

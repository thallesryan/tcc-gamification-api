package io.github.thallesyan.gamification_api.application.usecases;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Reward;
import io.github.thallesyan.gamification_api.domain.services.CreateReward;
import io.github.thallesyan.gamification_api.domain.services.FindRewards;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class RewardApplication {

    private final CreateReward createReward;
    private final FindRewards findRewards;

    public RewardApplication(CreateReward createReward, FindRewards findRewards) {
        this.createReward = createReward;
        this.findRewards = findRewards;
    }

    public Reward createReward(Reward reward) {
        return createReward.createReward(reward);
    }

    public List<Reward> findAllRewards() {
        return findRewards.findAllRewards();
    }

    public Optional<Reward> findRewardById(UUID identifier) {
        return findRewards.findRewardById(identifier);
    }
}


package io.github.thallesyan.gamification_api.application.usecases;

import io.github.thallesyan.gamification_api.application.exceptions.EntityNotFoundException;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Reward;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserReward;
import io.github.thallesyan.gamification_api.domain.entities.reward.Badge;
import io.github.thallesyan.gamification_api.domain.services.CreateReward;
import io.github.thallesyan.gamification_api.domain.services.FindBadge;
import io.github.thallesyan.gamification_api.domain.services.FindRewards;
import io.github.thallesyan.gamification_api.domain.services.FindUserRewards;
import io.github.thallesyan.gamification_api.domain.services.UpdateReward;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class RewardApplication {

    private final CreateReward createReward;
    private final FindRewards findRewards;
    private final FindBadge findBadge;
    private final FindUserRewards findUserRewards;
    private final UserApplication userApplication;
    private final UpdateReward updateReward;

    public RewardApplication(CreateReward createReward, FindRewards findRewards, FindBadge findBadge, 
                             FindUserRewards findUserRewards, UserApplication userApplication,
                             UpdateReward updateReward) {
        this.createReward = createReward;
        this.findRewards = findRewards;
        this.findBadge = findBadge;
        this.findUserRewards = findUserRewards;
        this.userApplication = userApplication;
        this.updateReward = updateReward;
    }

    public Reward createReward(Reward reward) {
        if(reward.getBadge().getIdentifier() != null){
            reward.setBadge(findBadge(reward.getBadge()));
        }

        return createReward.createReward(reward);
    }

    public List<Reward> findAllRewards() {
        return findRewards.findAllRewards();
    }

    public Optional<Reward> findRewardById(UUID identifier) {
        return findRewards.findRewardById(identifier);
    }

    public List<UserReward> findUserRewardsByEmail(String email, String platform) {
        var user = userApplication.findUserByEmail(email, platform);
        return findUserRewards.findByUser(user);
    }

    public Reward updateReward(UUID rewardId, Reward reward) {
        return updateReward.updateReward(rewardId, reward);
    }

    public Reward updateReward(UUID rewardId, Reward reward, Boolean isActive) {
        return updateReward.updateReward(rewardId, reward, isActive);
    }

    private Badge findBadge(Badge badge) {
        return findBadge.findBadgeById(badge.getIdentifier())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Badge",
                        "identifier",
                        badge.getIdentifier().toString()
                ));
    }
}


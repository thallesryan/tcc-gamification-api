package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.UpdateRewardBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Reward;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.RewardJpaPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.RewardPersistenceMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateRewardDb implements UpdateRewardBoundary {

    private final RewardJpaPersistence rewardJpaPersistence;
    private final RewardPersistenceMapper rewardPersistenceMapper;

    public UpdateRewardDb(RewardJpaPersistence rewardJpaPersistence, 
                          RewardPersistenceMapper rewardPersistenceMapper) {
        this.rewardJpaPersistence = rewardJpaPersistence;
        this.rewardPersistenceMapper = rewardPersistenceMapper;
    }

    @Override
    public Reward updateReward(UUID rewardId, Reward reward) {
        return updateReward(rewardId, reward, null);
    }

    @Override
    public Reward updateReward(UUID rewardId, Reward reward, Boolean isActive) {
        var existingRewardJPA = rewardJpaPersistence.findById(rewardId)
                .orElseThrow(() -> new RuntimeException("Reward not found with id: " + rewardId));

        // Atualizar apenas campos fornecidos (PATCH)
        if (reward.getName() != null) {
            existingRewardJPA.setName(reward.getName());
        }
        if (reward.getDescription() != null) {
            existingRewardJPA.setDescription(reward.getDescription());
        }
        if (reward.getPoints() != null) {
            existingRewardJPA.setPoints(reward.getPoints());
        }
        if (isActive != null) {
            existingRewardJPA.setIsActive(isActive);
        }

        var updatedRewardJPA = rewardJpaPersistence.save(existingRewardJPA);
        return rewardPersistenceMapper.toModel(updatedRewardJPA);
    }
}


package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.CreateRewardBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Reward;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.RewardJpaPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.RewardPersistenceMapper;
import org.springframework.stereotype.Service;

@Service
public class CreateRewardDb implements CreateRewardBoundary {

    private final RewardJpaPersistence rewardJpaPersistence;
    private final RewardPersistenceMapper rewardPersistenceMapper;

    public CreateRewardDb(RewardJpaPersistence rewardJpaPersistence, RewardPersistenceMapper rewardPersistenceMapper) {
        this.rewardJpaPersistence = rewardJpaPersistence;
        this.rewardPersistenceMapper = rewardPersistenceMapper;
    }

    @Override
    public Reward createReward(Reward reward) {
        var rewardJPA = rewardPersistenceMapper.toJPAEntity(reward);
        var rewardCreatedDb = rewardJpaPersistence.save(rewardJPA);
        return rewardPersistenceMapper.toModel(rewardCreatedDb);
    }
}


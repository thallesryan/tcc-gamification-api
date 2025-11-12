package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.FindRewardBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Reward;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.RewardJpaPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.RewardPersistenceMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FindRewardDb implements FindRewardBoundary {

    private final RewardJpaPersistence rewardJpaPersistence;
    private final RewardPersistenceMapper rewardPersistenceMapper;

    public FindRewardDb(RewardJpaPersistence rewardJpaPersistence, RewardPersistenceMapper rewardPersistenceMapper) {
        this.rewardJpaPersistence = rewardJpaPersistence;
        this.rewardPersistenceMapper = rewardPersistenceMapper;
    }

    @Override
    public List<Reward> findAllRewards() {
        return rewardJpaPersistence.findAll().stream()
                .map(rewardPersistenceMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Reward> findRewardById(UUID identifier) {
        return rewardJpaPersistence.findById(identifier)
                .map(rewardPersistenceMapper::toModel);
    }
}


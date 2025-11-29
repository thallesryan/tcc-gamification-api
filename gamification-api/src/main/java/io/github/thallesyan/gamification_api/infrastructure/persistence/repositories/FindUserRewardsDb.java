package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.FindUserRewardsBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserReward;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.UserRewardJpaPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.UserRewardPersistenceMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindUserRewardsDb implements FindUserRewardsBoundary {

    private final UserRewardJpaPersistence userRewardJpaPersistence;
    private final UserRewardPersistenceMapper userRewardPersistenceMapper;

    public FindUserRewardsDb(UserRewardJpaPersistence userRewardJpaPersistence,
                             UserRewardPersistenceMapper userRewardPersistenceMapper) {
        this.userRewardJpaPersistence = userRewardJpaPersistence;
        this.userRewardPersistenceMapper = userRewardPersistenceMapper;
    }

    @Override
    public List<UserReward> findByUser(User user) {
        return userRewardJpaPersistence.findByUser_Identifier(user.getIdentifier()).stream()
                .map(userRewardPersistenceMapper::toUserReward)
                .collect(Collectors.toList());
    }
}


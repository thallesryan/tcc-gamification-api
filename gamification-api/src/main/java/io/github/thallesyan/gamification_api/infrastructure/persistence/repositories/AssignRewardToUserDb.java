package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.AssignRewardToUserBoundary;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserReward;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.UserRewardJpaPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.UserRewardPersistenceMapper;
import org.springframework.stereotype.Service;

@Service
public class AssignRewardToUserDb implements AssignRewardToUserBoundary {

    private final UserRewardJpaPersistence userRewardJpaPersistence;
    private final UserRewardPersistenceMapper userRewardPersistenceMapper;

    public AssignRewardToUserDb(UserRewardJpaPersistence userRewardJpaPersistence, UserRewardPersistenceMapper userRewardPersistenceMapper) {
        this.userRewardJpaPersistence = userRewardJpaPersistence;
        this.userRewardPersistenceMapper = userRewardPersistenceMapper;
    }

    @Override
    public UserReward assignReward(UserReward userReward) {
        var userRewardJPA = userRewardPersistenceMapper.toUserRewardJPA(userReward);
        var userRewardCreatedDb = userRewardJpaPersistence.save(userRewardJPA);
        return userRewardPersistenceMapper.toUserReward(userRewardCreatedDb);
    }
}


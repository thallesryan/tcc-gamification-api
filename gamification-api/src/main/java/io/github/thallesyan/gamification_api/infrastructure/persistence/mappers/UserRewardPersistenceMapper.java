package io.github.thallesyan.gamification_api.infrastructure.persistence.mappers;

import io.github.thallesyan.gamification_api.domain.entities.progress.UserReward;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.UserRewardJPA;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserPersistenceMapper.class, RewardPersistenceMapper.class})
public interface UserRewardPersistenceMapper {

    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "reward", source = "reward")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "earnedAt", source = "earnedAt")
    @Mapping(target = "expiresAt", source = "expiresAt")
    @Mapping(target = "pointsValue", source = "pointsValue")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserRewardJPA toUserRewardJPA(UserReward userReward);

    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "reward", source = "reward")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "earnedAt", source = "earnedAt")
    @Mapping(target = "expiresAt", source = "expiresAt")
    @Mapping(target = "pointsValue", source = "pointsValue")
    UserReward toUserReward(UserRewardJPA userRewardJPA);
}


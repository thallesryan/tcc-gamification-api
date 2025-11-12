package io.github.thallesyan.gamification_api.infrastructure.persistence.mappers;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Reward;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.RewardJPA;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {BadgePersistenceMapper.class})
public interface RewardPersistenceMapper {

    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "points", source = "points")
    @Mapping(target = "badge", source = "badge")
//    @Mapping(target = "rewardType", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "expirationDays", ignore = true)
    @Mapping(target = "maxUsesPerUser", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    RewardJPA toJPAEntity(Reward reward);

    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "points", source = "points")
    @Mapping(target = "badge", source = "badge")
    Reward toModel(RewardJPA rewardJPA);
}


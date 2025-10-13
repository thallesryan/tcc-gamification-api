package io.github.thallesyan.gamification_api.infrastructure.persistence.mappers;

import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionGoalProgress;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.UserMissionGoalProgressJPA;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {GoalPersistenceMapper.class, UserMissionGoalPersistenceMapper.class})
public interface UserMissionGoalPersistenceMapper {

//    @Mapping(target = "userMissionProgress.id", source = "userMissionProgress.id", ignore  = true)
    UserMissionGoalProgress JpaEntityToModel(UserMissionGoalProgressJPA userMissionProgressJPA);
}

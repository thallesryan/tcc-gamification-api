package io.github.thallesyan.gamification_api.infrastructure.persistence.mappers;

import io.github.thallesyan.gamification_api.domain.entities.progress.UserMission;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionGoal;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.UserMissionGoalProgressJPA;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {GoalPersistenceMapper.class, UserMissionGoalPersistenceMapper.class})
public interface UserMissionGoalPersistenceMapper {
    UserMissionGoal JpaEntityToModel(UserMissionGoalProgressJPA userMissionProgressJPA);
}

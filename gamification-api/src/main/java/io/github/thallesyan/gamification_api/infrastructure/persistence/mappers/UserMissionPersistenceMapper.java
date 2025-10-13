package io.github.thallesyan.gamification_api.infrastructure.persistence.mappers;

import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.UserMissionProgressJPA;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {GoalPersistenceMapper.class, MissionPersistenceMapper.class, UserMissionPersistenceMapper.class, UserMissionPersistenceMapper.class})
public interface UserMissionPersistenceMapper {
    UserMissionProgress JpaEntityToModel(UserMissionProgressJPA userMissionProgressJPA);
}

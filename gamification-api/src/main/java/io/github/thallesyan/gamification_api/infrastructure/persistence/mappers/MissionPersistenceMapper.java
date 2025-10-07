package io.github.thallesyan.gamification_api.infrastructure.persistence.mappers;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.MissionJPA;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {GoalPersistenceMapper.class})
public interface MissionPersistenceMapper {

    @Mapping(target = "goals", source = "goal")
    @Mapping(target = "rules", ignore = true)
    @Mapping(target = "difficultyLevel", ignore = true)
    @Mapping(target = "estimatedDurationHours", ignore = true)
    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    MissionJPA toMissionJPA(Mission mission);

    @Mapping(target = "goal", source = "goals")
    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    Mission toMission(MissionJPA missionJPA);
}

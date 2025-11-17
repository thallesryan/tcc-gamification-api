package io.github.thallesyan.gamification_api.infrastructure.persistence.mappers;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.MissionJPA;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Duration;

@Mapper(componentModel = "spring", uses = {GoalPersistenceMapper.class, RulePersistenceMapper.class, RewardPersistenceMapper.class})
public interface MissionPersistenceMapper {

    @Mapping(target = "goals", source = "goals")
    @Mapping(target = "rules", source = "rules")
    @Mapping(target = "reward", source = "reward")
    @Mapping(target = "difficultyLevel", ignore = true)
    @Mapping(target = "estimatedDuration", source = "estimatedDuration")
    @Mapping(target = "points", source = "points")
    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    MissionJPA toMissionJPA(Mission mission);

    @Mapping(target = "goals", source = "goals")
    @Mapping(target = "rules", source = "rules")
    @Mapping(target = "reward", source = "reward")
    @Mapping(target = "points", source = "points")
    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "estimatedDuration", source = "estimatedDuration")
    Mission toMission(MissionJPA missionJPA);



}

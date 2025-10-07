package io.github.thallesyan.gamification_api.infrastructure.persistence.mappers;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Goal;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.GoalJPA;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GoalPersistenceMapper {

    @Mapping(target = "mission", ignore = true)
    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    GoalJPA toGoalJPA(Goal goal);

    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    Goal toGoal(GoalJPA goalJPA);
}

package io.github.thallesyan.gamification_api.infrastructure.web.mappers;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Goal;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.GoalDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.UserGoalProgressResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GoalMapper {

    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    Goal toGoal(GoalDTO goalDTO);

    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    GoalDTO toGoalDTO(Goal goal);

    UserGoalProgressResponseDTO toGoalResponseDTO(Goal goal);
}

package io.github.thallesyan.gamification_api.infrastructure.web.mappers;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Goal;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionGoalProgress;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.GoalDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.GoalResponseDTO;
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

    @Mapping(target = "identifier", ignore = true)
    @Mapping(target = "title", source = "goalUpdateRequestDTO.title")
    @Mapping(target = "description", source = "goalUpdateRequestDTO.description")
    @Mapping(target = "order", ignore = true)
    Goal toGoal(io.github.thallesyan.gamification_api.infrastructure.web.dto.GoalUpdateRequestDTO goalUpdateRequestDTO);

    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "order", source = "order")
    GoalResponseDTO toGoalResponseDTO(Goal goal);
}

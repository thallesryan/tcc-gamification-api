package io.github.thallesyan.gamification_api.infrastructure.web.mappers;

import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionGoalProgress;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.UserGoalProgressResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMissionGoalMapper {

    @Mappings({
            @Mapping(source = "startDate", target = "startTime"),
            @Mapping(source = "goal.title", target = "title"),
            @Mapping(source = "goal.description", target = "description")
    })
    UserGoalProgressResponseDTO  userGoalToUserGoalProgress(UserMissionGoalProgress userGoalProgressResponseDTO);
}

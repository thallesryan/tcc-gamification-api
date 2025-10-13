package io.github.thallesyan.gamification_api.infrastructure.web.mappers;

import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.MissionStartResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {GoalMapper.class, UserMissionGoalMapper.class})
public interface UserMissionMapper {

    @Mappings({
            @Mapping(source = "startDate", target = "startTime"),
            @Mapping(source = "mission.title", target = "title"),
            @Mapping(source = "mission.description", target = "description")
    })
     MissionStartResponseDTO toStartMissionResponseDTO(UserMissionProgress userMissionProgress);
}

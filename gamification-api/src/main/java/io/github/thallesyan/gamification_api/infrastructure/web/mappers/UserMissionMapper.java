package io.github.thallesyan.gamification_api.infrastructure.web.mappers;

import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.MissionProgressResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.MissionStartResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {GoalMapper.class, UserMissionGoalMapper.class})
public interface UserMissionMapper {

    @Mappings({
            @Mapping(source = "startDate", target = "start"),
            @Mapping(source = "completionDate", target = "end"),
            @Mapping(source = "mission.title", target = "title"),
            @Mapping(source = "mission.description", target = "description")
    })
     MissionStartResponseDTO toStartMissionResponseDTO(UserMissionProgress userMissionProgress);

    @Mappings({
            @Mapping(source = "startDate", target = "start"),
            @Mapping(source = "completionDate", target = "end"),
            @Mapping(source = "mission.title", target = "title"),
            @Mapping(source = "mission.description", target = "description")
    })
    MissionProgressResponseDTO toMissionProgressResponseDTO(UserMissionProgress userMissionProgress);
}

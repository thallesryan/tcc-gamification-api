package io.github.thallesyan.gamification_api.application.mappers;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.MissionRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.MissionResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {GoalMapper.class})
public interface MissionMapper {

    @Mapping(target = "goal", source = "goals")
    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    Mission toMission(MissionResponseDTO missionResponseDTO);

    @Mapping(target = "goal", source = "goals")
    @Mapping(target = "identifier", ignore = true)
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    Mission toMission(MissionRequestDTO missionRequestDTO);

    @Mapping(target = "goals", source = "goal")
    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "difficultyLevel", ignore = true)
    MissionResponseDTO toMissionResponseDTO(Mission mission);
}

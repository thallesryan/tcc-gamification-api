package io.github.thallesyan.gamification_api.infrastructure.web.mappers;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.MissionCreationRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.MissionUpdateRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.MissionResponseDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.Duration;

@Mapper(componentModel = "spring", uses = {GoalMapper.class, RuleMapper.class, RewardMapper.class, BadgeMapper.class})
public interface MissionMapper {

    @Mapping(target = "goals", source = "goals")
    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    Mission toMission(MissionResponseDTO missionResponseDTO);

    @Mapping(target = "goals", source = "missionCreationRequestDTO.goals")
    @Mapping(target = "identifier", ignore = true)
    @Mapping(target = "title", source = "missionCreationRequestDTO.title")
    @Mapping(target = "description", source = "missionCreationRequestDTO.description")
    @Mapping(target = "points", source = "missionCreationRequestDTO.points")
    //@Mapping(target = "reward.badge.rarity.platform.name", source = "platform")
    Mission toMission(MissionCreationRequestDTO missionCreationRequestDTO, String platform);

    @Mapping(target = "goals", source = "goals")
    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "difficultyLevel", ignore = true)
    @Mapping(target = "points", source = "points")
    @Mapping(target = "estimatedDuration", ignore = true)
    MissionResponseDTO toMissionResponseDTO(Mission mission);

    @AfterMapping
    default void mapEstimatedDuration(@MappingTarget MissionResponseDTO dto, Mission mission) {
        if (mission.getEstimatedDuration() != null) {
            Duration duration = mission.getEstimatedDuration();
            long days = duration.toDays();
            long hours = duration.toHoursPart();
            long minutes = duration.toMinutesPart();
            long seconds = duration.toSecondsPart();
            dto.setEstimatedDuration(String.format("Days: %d, hours: %d, minutes: %d, seconds: %d", days, hours, minutes, seconds));
        }
    }

    @Mapping(target = "goals", ignore = true)
    @Mapping(target = "identifier", ignore = true)
    @Mapping(target = "title", source = "missionUpdateRequestDTO.title")
    @Mapping(target = "description", source = "missionUpdateRequestDTO.description")
    @Mapping(target = "points", source = "missionUpdateRequestDTO.points")
    @Mapping(target = "rule", ignore = true)
    @Mapping(target = "reward", ignore = true)
    @Mapping(target = "estimatedDuration", ignore = true)
    Mission toMission(MissionUpdateRequestDTO missionUpdateRequestDTO);
}

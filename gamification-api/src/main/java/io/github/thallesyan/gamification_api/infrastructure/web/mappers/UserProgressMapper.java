package io.github.thallesyan.gamification_api.infrastructure.web.mappers;

import io.github.thallesyan.gamification_api.domain.entities.progress.UserProgress;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.UserProgressResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserProgressMapper {

    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "userId", source = "user.identifier")
    @Mapping(target = "userName", source = "user.name")
    @Mapping(target = "userEmail", source = "user.email")
    @Mapping(target = "totalPoints", source = "totalPoints")
    @Mapping(target = "currentLevel", source = "currentLevel")
    @Mapping(target = "badgesEarned", source = "badgesEarned")
    @Mapping(target = "missionsCompleted", source = "missionsCompleted")
    @Mapping(target = "goalsCompleted", source = "goalsCompleted")
    UserProgressResponseDTO toUserProgressResponseDTO(UserProgress userProgress);
}


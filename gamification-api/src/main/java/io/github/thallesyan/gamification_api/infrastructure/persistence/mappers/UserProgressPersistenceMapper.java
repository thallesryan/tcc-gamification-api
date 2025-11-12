package io.github.thallesyan.gamification_api.infrastructure.persistence.mappers;

import io.github.thallesyan.gamification_api.domain.entities.progress.UserProgress;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.UserProgressJPA;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserPersistenceMapper.class})
public interface UserProgressPersistenceMapper {

    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "totalPoints", source = "totalPoints")
    @Mapping(target = "currentLevel", source = "currentLevel")
    @Mapping(target = "badgesEarned", source = "badgesEarned")
    @Mapping(target = "missionsCompleted", source = "missionsCompleted")
    @Mapping(target = "goalsCompleted", source = "goalsCompleted")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserProgressJPA toUserProgressJPA(UserProgress userProgress);

    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "totalPoints", source = "totalPoints")
    @Mapping(target = "currentLevel", source = "currentLevel")
    @Mapping(target = "badgesEarned", source = "badgesEarned")
    @Mapping(target = "missionsCompleted", source = "missionsCompleted")
    @Mapping(target = "goalsCompleted", source = "goalsCompleted")
    UserProgress toUserProgress(UserProgressJPA userProgressJPA);
}


package io.github.thallesyan.gamification_api.infrastructure.persistence.mappers;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.PlatformJPA;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlatformPersistenceMapper {

    PlatformJPA toJPAEntity(Platform platform);
    Platform toModel(PlatformJPA platform);
}

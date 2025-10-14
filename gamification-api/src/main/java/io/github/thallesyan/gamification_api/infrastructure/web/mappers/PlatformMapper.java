package io.github.thallesyan.gamification_api.infrastructure.web.mappers;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.PlatformCreationRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.PlatformRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.PlatformCreationResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlatformMapper {
    Platform toModel(PlatformRequestDTO dto);

    Platform toModel(PlatformCreationRequestDTO dto);

    PlatformCreationResponseDTO toCreationResponseDTO(Platform platform);
}

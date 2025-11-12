package io.github.thallesyan.gamification_api.infrastructure.web.mappers;

import io.github.thallesyan.gamification_api.domain.entities.reward.Badge;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.BadgeCreationRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.BadgeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RarityMapper.class})
public interface BadgeMapper {

    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "rarity.value", source = "rarity")
    Badge toBadge(BadgeCreationRequestDTO dto);

    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "rarity", source = "rarity")
    BadgeResponseDTO toBadgeResponseDTO(Badge badge);
}


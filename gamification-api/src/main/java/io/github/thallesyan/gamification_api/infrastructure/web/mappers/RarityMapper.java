package io.github.thallesyan.gamification_api.infrastructure.web.mappers;

import io.github.thallesyan.gamification_api.domain.entities.reward.Rarity;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.RarityResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RarityMapper {

    @Mapping(target = "value", source = "value")
    @Mapping(target = "points", source = "points")
    RarityResponseDTO toRarityResponseDTO(Rarity rarity);
}


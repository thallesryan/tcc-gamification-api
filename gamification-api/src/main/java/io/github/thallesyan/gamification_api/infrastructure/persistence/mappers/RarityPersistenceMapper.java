package io.github.thallesyan.gamification_api.infrastructure.persistence.mappers;

import io.github.thallesyan.gamification_api.domain.entities.reward.Rarity;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.reward.RarityJPA;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PlatformPersistenceMapper.class})
public interface RarityPersistenceMapper {

    @Mapping(target = "nivel", source = "value")
    @Mapping(target = "points", source = "points")
    @Mapping(target = "platform", source = "platform")
    RarityJPA toJPAEntity(Rarity rarity);

    @Mapping(target = "value", source = "nivel")
    @Mapping(target = "points", source = "points")
    @Mapping(target = "platform", source = "platform")
    Rarity toModel(RarityJPA rarityJPA);
}


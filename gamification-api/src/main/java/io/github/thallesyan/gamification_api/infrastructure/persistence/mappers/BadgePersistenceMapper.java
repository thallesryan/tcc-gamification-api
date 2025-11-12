package io.github.thallesyan.gamification_api.infrastructure.persistence.mappers;

import io.github.thallesyan.gamification_api.domain.entities.reward.Badge;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.reward.BadgeJPA;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RarityPersistenceMapper.class})
public interface BadgePersistenceMapper {

    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "rarity", source = "rarity")
    BadgeJPA toJPAEntity(Badge badge);

    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "rarity", source = "rarity")
    Badge toModel(BadgeJPA badgeJPA);
}


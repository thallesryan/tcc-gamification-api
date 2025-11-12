package io.github.thallesyan.gamification_api.infrastructure.web.mappers;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Rule;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.RuleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RuleMapper {

    @Mapping(target = "identifier", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "durationLimit", source = "durationLimit")
    Rule toRule(RuleDTO ruleDTO);
}


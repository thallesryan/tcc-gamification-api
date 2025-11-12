package io.github.thallesyan.gamification_api.infrastructure.persistence.mappers;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Rule;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.RuleJPA;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Duration;

@Mapper(componentModel = "spring")
public interface RulePersistenceMapper {

    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "priority", ignore = true)
    @Mapping(target = "mission", ignore = true)
    @Mapping(target = "durationLimitMillis", expression = "java(rule.getDurationLimit() != null ? rule.getDurationLimit().toMillis() : null)")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    RuleJPA toJPAEntity(Rule rule);

    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "durationLimit", expression = "java(ruleJPA.getDurationLimitMillis() != null ? java.time.Duration.ofMillis(ruleJPA.getDurationLimitMillis()) : null)")
    Rule toModel(RuleJPA ruleJPA);
}


package io.github.thallesyan.gamification_api.domain.entities.foundation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para Rule")
class RuleTest {

    @Test
    @DisplayName("Deve criar Rule vazio com construtor padrão")
    void deveCriarRuleVazio() {
        Rule rule = new Rule();

        assertNull(rule.getIdentifier());
        assertNull(rule.getName());
        assertNull(rule.getDescription());
        assertNull(rule.getDurationLimit());
    }

    @Test
    @DisplayName("Deve criar Rule com durationLimit")
    void deveCriarRuleComDurationLimit() {
        Duration durationLimit = Duration.ofHours(2);
        Rule rule = new Rule();
        rule.setDurationLimit(durationLimit);

        assertEquals(durationLimit, rule.getDurationLimit());
    }

    @Test
    @DisplayName("Deve criar Rule com todos os campos")
    void deveCriarRuleComTodosOsCampos() {
        Duration durationLimit = Duration.ofMinutes(90);
        Rule rule = new Rule();
        rule.setName("Test Rule");
        rule.setDescription("Test Description");
        rule.setDurationLimit(durationLimit);

        assertEquals("Test Rule", rule.getName());
        assertEquals("Test Description", rule.getDescription());
        assertEquals(durationLimit, rule.getDurationLimit());
    }
}


package io.github.thallesyan.gamification_api.domain.entities.foundation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para Goal")
class GoalTest {

    @Test
    @DisplayName("Deve criar Goal com título, descrição e ordem")
    void deveCriarGoalComTituloDescricaoEOrdem() {
        Goal goal = new Goal("Goal Title", "Goal Description", 1);

        assertEquals("Goal Title", goal.getTitle());
        assertEquals("Goal Description", goal.getDescription());
        assertEquals(1, goal.getOrder());
    }

    @Test
    @DisplayName("Deve criar Goal vazio com construtor padrão")
    void deveCriarGoalVazio() {
        Goal goal = new Goal();

        assertNull(goal.getIdentifier());
        assertNull(goal.getTitle());
        assertNull(goal.getDescription());
        assertNull(goal.getOrder());
    }
}


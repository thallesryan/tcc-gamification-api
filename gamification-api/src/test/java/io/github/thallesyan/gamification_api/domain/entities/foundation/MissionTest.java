package io.github.thallesyan.gamification_api.domain.entities.foundation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para Mission")
class MissionTest {

    @Test
    @DisplayName("Deve criar Mission com título, descrição e goals")
    void deveCriarMissionComTituloDescricaoEGoads() {
        Goal goal1 = new Goal("Goal 1", "Description 1", 1);
        Goal goal2 = new Goal("Goal 2", "Description 2", 2);
        List<Goal> goals = Arrays.asList(goal1, goal2);

        Mission mission = new Mission("Mission Title", "Mission Description", goals);

        assertEquals("Mission Title", mission.getTitle());
        assertEquals("Mission Description", mission.getDescription());
        assertEquals(goals, mission.getGoals());
    }

    @Test
    @DisplayName("Deve criar Mission com byIdentifier usando String")
    void deveCriarMissionComByIdentifierString() {
        String identifierString = "550e8400-e29b-41d4-a716-446655440000";

        Mission mission = Mission.byIdentifier(identifierString);

        assertEquals(UUID.fromString(identifierString), mission.getIdentifier());
    }

    @Test
    @DisplayName("Deve criar Mission com byIdentifier usando UUID")
    void deveCriarMissionComByIdentifierUUID() {
        UUID identifier = UUID.randomUUID();

        Mission mission = Mission.byIdentifier(identifier);

        assertEquals(identifier, mission.getIdentifier());
    }

    @Test
    @DisplayName("Deve criar Mission vazia com construtor padrão")
    void deveCriarMissionVazia() {
        Mission mission = new Mission();

        assertNull(mission.getIdentifier());
        assertNull(mission.getTitle());
        assertNull(mission.getDescription());
        assertNull(mission.getPoints());
        assertNull(mission.getGoals());
        assertNull(mission.getRule());
        assertNull(mission.getReward());
    }
}


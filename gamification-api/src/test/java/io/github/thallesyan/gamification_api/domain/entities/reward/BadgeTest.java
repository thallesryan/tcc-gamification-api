package io.github.thallesyan.gamification_api.domain.entities.reward;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para Badge")
class BadgeTest {

    @Test
    @DisplayName("Deve criar Badge com byIdentifier usando String")
    void deveCriarBadgeComByIdentifierString() {
        String identifierString = "550e8400-e29b-41d4-a716-446655440000";

        Badge badge = Badge.byIdentifier(identifierString);

        assertEquals(UUID.fromString(identifierString), badge.getIdentifier());
    }

    @Test
    @DisplayName("Deve criar Badge com byIdentifier usando UUID")
    void deveCriarBadgeComByIdentifierUUID() {
        UUID identifier = UUID.randomUUID();

        Badge badge = Badge.byIdentifier(identifier);

        assertEquals(identifier, badge.getIdentifier());
    }

    @Test
    @DisplayName("Deve criar Badge vazio com construtor padrão")
    void deveCriarBadgeVazio() {
        Badge badge = new Badge();

        assertNull(badge.getIdentifier());
        assertNull(badge.getName());
        assertNull(badge.getDescription());
        assertNull(badge.getRarity());
    }
}


package io.github.thallesyan.gamification_api.domain.entities.foundation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para User")
class UserTest {

    @Test
    @DisplayName("Deve criar User com byIdentifier usando String")
    void deveCriarUserComByIdentifierString() {
        String identifierString = "550e8400-e29b-41d4-a716-446655440000";

        User user = User.byIdentifier(identifierString);

        assertEquals(UUID.fromString(identifierString), user.getIdentifier());
    }

    @Test
    @DisplayName("Deve criar User com byIdentifier usando UUID")
    void deveCriarUserComByIdentifierUUID() {
        UUID identifier = UUID.randomUUID();

        User user = User.byIdentifier(identifier);

        assertEquals(identifier, user.getIdentifier());
    }

    @Test
    @DisplayName("Deve criar User vazio com construtor padrão")
    void deveCriarUserVazio() {
        User user = new User();

        assertNull(user.getIdentifier());
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getDateOfBirth());
        assertNull(user.getPlatform());
    }

    @Test
    @DisplayName("Deve criar User com todos os campos")
    void deveCriarUserComTodosOsCampos() {
        UUID identifier = UUID.randomUUID();
        String name = "Test User";
        String email = "test@example.com";
        Platform platform = new Platform("test-platform", 1000, 0.75);

        User user = new User(identifier, name, email, null, platform);

        assertEquals(identifier, user.getIdentifier());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(platform, user.getPlatform());
    }
}


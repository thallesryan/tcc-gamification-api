package io.github.thallesyan.gamification_api.domain.entities.reward;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para Rarity")
class RarityTest {

    @Test
    @DisplayName("Deve criar Rarity vazio com construtor padrão")
    void deveCriarRarityVazio() {
        Rarity rarity = new Rarity();

        assertNull(rarity.getValue());
        assertNull(rarity.getPoints());
        assertNull(rarity.getPlatform());
    }

    @Test
    @DisplayName("Deve criar Rarity com todos os campos")
    void deveCriarRarityComTodosOsCampos() {
        RarityEnum value = RarityEnum.COMMON;
        Integer points = 100;
        Platform platform = new Platform("test-platform", 1000, 0.75);

        Rarity rarity = new Rarity(value, points, platform);

        assertEquals(value, rarity.getValue());
        assertEquals(points, rarity.getPoints());
        assertEquals(platform, rarity.getPlatform());
    }
}


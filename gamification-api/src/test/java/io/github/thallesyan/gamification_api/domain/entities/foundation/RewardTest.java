package io.github.thallesyan.gamification_api.domain.entities.foundation;

import io.github.thallesyan.gamification_api.domain.entities.reward.Badge;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para Reward")
class RewardTest {

    @Test
    @DisplayName("Deve criar Reward vazio com construtor padrão")
    void deveCriarRewardVazio() {
        Reward reward = new Reward();

        assertNull(reward.getIdentifier());
        assertNull(reward.getName());
        assertNull(reward.getDescription());
        assertNull(reward.getPoints());
        assertNull(reward.getBadge());
    }

    @Test
    @DisplayName("Deve criar Reward com todos os campos")
    void deveCriarRewardComTodosOsCampos() {
        Badge badge = new Badge();
        Reward reward = new Reward();
        reward.setPoints(100);
        reward.setBadge(badge);

        assertEquals(100, reward.getPoints());
        assertEquals(badge, reward.getBadge());
    }
}


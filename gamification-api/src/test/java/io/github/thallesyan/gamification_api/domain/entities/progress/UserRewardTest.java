package io.github.thallesyan.gamification_api.domain.entities.progress;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Reward;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para UserReward")
class UserRewardTest {

    @Test
    @DisplayName("Deve criar UserReward com valores padrão quando criado com User e Reward")
    void deveCriarUserRewardComValoresPadrao() {
        User user = new User();
        Reward reward = new Reward();
        reward.setPoints(100);

        UserReward userReward = new UserReward(user, reward);

        assertEquals(user, userReward.getUser());
        assertEquals(reward, userReward.getReward());
        assertEquals(UserReward.UserRewardStatus.EARNED, userReward.getStatus());
        assertNotNull(userReward.getEarnedAt());
        assertEquals(100, userReward.getPointsValue());
    }

    @Test
    @DisplayName("Deve criar UserReward com earnedAt sendo LocalDateTime.now()")
    void deveCriarUserRewardComEarnedAtAtual() {
        User user = new User();
        Reward reward = new Reward();
        LocalDateTime beforeCreation = LocalDateTime.now().minusSeconds(1);

        UserReward userReward = new UserReward(user, reward);
        LocalDateTime afterCreation = LocalDateTime.now().plusSeconds(1);

        assertNotNull(userReward.getEarnedAt());
        assertTrue(userReward.getEarnedAt().isAfter(beforeCreation) || userReward.getEarnedAt().isEqual(beforeCreation));
        assertTrue(userReward.getEarnedAt().isBefore(afterCreation) || userReward.getEarnedAt().isEqual(afterCreation));
    }

    @Test
    @DisplayName("Deve criar UserReward com pointsValue null quando reward é null")
    void deveCriarUserRewardComPointsValueNullQuandoRewardNull() {
        User user = new User();

        UserReward userReward = new UserReward(user, null);

        assertNull(userReward.getReward());
        assertNull(userReward.getPointsValue());
    }

    @Test
    @DisplayName("Deve criar UserReward com pointsValue null quando reward.getPoints() é null")
    void deveCriarUserRewardComPointsValueNullQuandoRewardPointsNull() {
        User user = new User();
        Reward reward = new Reward();
        reward.setPoints(null);

        UserReward userReward = new UserReward(user, reward);

        assertNull(userReward.getPointsValue());
    }

    @Test
    @DisplayName("Deve criar UserReward vazio com construtor padrão")
    void deveCriarUserRewardVazio() {
        UserReward userReward = new UserReward();

        assertNull(userReward.getUser());
        assertNull(userReward.getReward());
        assertEquals(UserReward.UserRewardStatus.EARNED, userReward.getStatus());
    }
}


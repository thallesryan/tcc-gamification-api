package io.github.thallesyan.gamification_api.application.usecases;

import io.github.thallesyan.gamification_api.application.exceptions.UserNotFoundException;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Reward;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserProgress;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserReward;
import io.github.thallesyan.gamification_api.domain.entities.reward.Badge;
import io.github.thallesyan.gamification_api.domain.entities.reward.Rarity;
import io.github.thallesyan.gamification_api.domain.entities.reward.RarityEnum;
import io.github.thallesyan.gamification_api.domain.services.FindUserProgress;
import io.github.thallesyan.gamification_api.domain.services.UpdateUserProgress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes para UserProgressPoints")
class UserProgressPointsTest {

    @Mock
    private FindUserProgress findUserProgress;

    @Mock
    private UpdateUserProgress updateUserProgress;

    @InjectMocks
    private UserProgressPoints userProgressPoints;

    private User user;
    private Platform platform;
    private UserProgress userProgress;
    private Mission mission;
    private UserMissionProgress userMissionProgress;

    @BeforeEach
    void setUp() {
        platform = new Platform("test-platform", 1000, 0.75);
        user = new User();
        user.setPlatform(platform);
        
        userProgress = new UserProgress(user);
        userProgress.setTotalPoints(0);
        userProgress.setCurrentLevel(1);

        mission = new Mission();
        mission.setPoints(100);

        userMissionProgress = new UserMissionProgress();
        userMissionProgress.setUser(user);
        userMissionProgress.setMission(mission);
    }

    @Test
    @DisplayName("Deve adicionar pontos de missão corretamente sem level up")
    void deveAdicionarPontosDeMissaoSemLevelUp() {
        when(findUserProgress.findByUser(user)).thenReturn(Optional.of(userProgress));
        when(updateUserProgress.updateUserProgress(any(UserProgress.class))).thenReturn(userProgress);

        UserProgress result = userProgressPoints.addMissionPoints(userMissionProgress);

        assertEquals(100, userProgress.getTotalPoints());
        assertEquals(1, userProgress.getMissionsCompleted());
        verify(findUserProgress).findByUser(user);
        verify(updateUserProgress).updateUserProgress(userProgress);
    }

    @Test
    @DisplayName("Deve lançar UserNotFoundException quando UserProgress não for encontrado")
    void deveLancarUserNotFoundExceptionQuandoUserProgressNaoEncontrado() {
        when(findUserProgress.findByUser(user)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userProgressPoints.addMissionPoints(userMissionProgress);
        });

        verify(findUserProgress).findByUser(user);
        verify(updateUserProgress, never()).updateUserProgress(any());
    }

    @Test
    @DisplayName("Deve fazer level up quando pontos suficientes")
    void deveFazerLevelUpQuandoPontosSuficientes() {
        userProgress.setTotalPoints(900);
        mission.setPoints(1000);
        when(findUserProgress.findByUser(user)).thenReturn(Optional.of(userProgress));
        when(updateUserProgress.updateUserProgress(any(UserProgress.class))).thenReturn(userProgress);

        userProgressPoints.addMissionPoints(userMissionProgress);

        assertEquals(2, userProgress.getCurrentLevel());
        verify(updateUserProgress).updateUserProgress(userProgress);
    }

    @Test
    @DisplayName("Deve adicionar pontos de reward corretamente")
    void deveAdicionarPontosDeRewardCorretamente() {
        Reward reward = new Reward();
        reward.setPoints(50);
        UserReward userReward = new UserReward(user, reward);
        
        when(findUserProgress.findByUser(user)).thenReturn(Optional.of(userProgress));
        when(updateUserProgress.updateUserProgress(any(UserProgress.class))).thenReturn(userProgress);

        userProgressPoints.addRewardPoints(userReward);

        assertEquals(50, userProgress.getTotalPoints());
        verify(findUserProgress).findByUser(user);
        verify(updateUserProgress).updateUserProgress(userProgress);
    }

    @Test
    @DisplayName("Não deve adicionar pontos quando reward for null")
    void naoDeveAdicionarPontosQuandoRewardForNull() {
        UserReward userReward = new UserReward(user, null);

        userProgressPoints.addRewardPoints(userReward);

        assertEquals(0, userProgress.getTotalPoints());
        verify(findUserProgress, never()).findByUser(any());
        verify(updateUserProgress, never()).updateUserProgress(any());
    }

    @Test
    @DisplayName("Não deve adicionar pontos quando reward.getPoints() for null")
    void naoDeveAdicionarPontosQuandoRewardPointsForNull() {
        Reward reward = new Reward();
        reward.setPoints(null);
        UserReward userReward = new UserReward(user, reward);

        userProgressPoints.addRewardPoints(userReward);

        assertEquals(0, userProgress.getTotalPoints());
        verify(findUserProgress, never()).findByUser(any());
        verify(updateUserProgress, never()).updateUserProgress(any());
    }

    @Test
    @DisplayName("Deve adicionar pontos de badge quando reward tiver badge")
    void deveAdicionarPontosDeBadgeQuandoRewardTiverBadge() {
        Rarity rarity = new Rarity();
        rarity.setPoints(25);
        Badge badge = new Badge();
        badge.setRarity(rarity);
        
        Reward reward = new Reward();
        reward.setPoints(50);
        reward.setBadge(badge);
        
        UserReward userReward = new UserReward(user, reward);
        
        when(findUserProgress.findByUser(user)).thenReturn(Optional.of(userProgress));
        when(updateUserProgress.updateUserProgress(any(UserProgress.class))).thenReturn(userProgress);

        userProgressPoints.addRewardPoints(userReward);

        assertEquals(75, userProgress.getTotalPoints());
        assertEquals(1, userProgress.getBadgesEarned());
        verify(findUserProgress).findByUser(user);
        verify(updateUserProgress).updateUserProgress(userProgress);
    }
}


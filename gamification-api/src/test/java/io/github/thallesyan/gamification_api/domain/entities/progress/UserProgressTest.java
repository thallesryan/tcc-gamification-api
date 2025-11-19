package io.github.thallesyan.gamification_api.domain.entities.progress;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para UserProgress")
class UserProgressTest {

    private User user;
    private Platform platform;

    @BeforeEach
    void setUp() {
        platform = new Platform("test-platform", 1000, 0.75);
        user = new User();
        user.setPlatform(platform);
    }

    @Test
    @DisplayName("Deve criar UserProgress com valores padrão quando criado com User")
    void deveCriarUserProgressComValoresPadrao() {
        UserProgress userProgress = new UserProgress(user);

        assertEquals(0, userProgress.getTotalPoints());
        assertEquals(1, userProgress.getCurrentLevel());
        assertEquals(0, userProgress.getBadgesEarned());
        assertEquals(0, userProgress.getMissionsCompleted());
        assertEquals(0, userProgress.getGoalsCompleted());
        assertEquals(user, userProgress.getUser());
    }

    @Test
    @DisplayName("Deve adicionar pontos corretamente")
    void deveAdicionarPontosCorretamente() {
        UserProgress userProgress = new UserProgress(user);

        Integer result = userProgress.addMissionPoints(100);

        assertEquals(100, userProgress.getTotalPoints());
        assertEquals(100, result);
    }

    @Test
    @DisplayName("Deve adicionar pontos múltiplas vezes")
    void deveAdicionarPontosMultiplasVezes() {
        UserProgress userProgress = new UserProgress(user);

        userProgress.addMissionPoints(50);
        userProgress.addMissionPoints(75);
        Integer result = userProgress.addMissionPoints(25);

        assertEquals(150, userProgress.getTotalPoints());
        assertEquals(150, result);
    }

    @Test
    @DisplayName("Deve incrementar missões completadas")
    void deveIncrementarMissoesCompletadas() {
        UserProgress userProgress = new UserProgress(user);

        userProgress.addMissionCompleted();

        assertEquals(1, userProgress.getMissionsCompleted());
    }

    @Test
    @DisplayName("Deve incrementar missões completadas múltiplas vezes")
    void deveIncrementarMissoesCompletadasMultiplasVezes() {
        UserProgress userProgress = new UserProgress(user);

        userProgress.addMissionCompleted();
        userProgress.addMissionCompleted();
        userProgress.addMissionCompleted();

        assertEquals(3, userProgress.getMissionsCompleted());
    }

    @Test
    @DisplayName("Deve incrementar goals completados")
    void deveIncrementarGoalsCompletados() {
        UserProgress userProgress = new UserProgress(user);

        userProgress.addGoalCompleted();

        assertEquals(1, userProgress.getGoalsCompleted());
    }

    @Test
    @DisplayName("Deve incrementar goals completados múltiplas vezes")
    void deveIncrementarGoalsCompletadosMultiplasVezes() {
        UserProgress userProgress = new UserProgress(user);

        userProgress.addGoalCompleted();
        userProgress.addGoalCompleted();

        assertEquals(2, userProgress.getGoalsCompleted());
    }

    @Test
    @DisplayName("Deve retornar true quando pontos suficientes para level up")
    void deveRetornarTrueQuandoPontosSuficientesParaLevelUp() {
        UserProgress userProgress = new UserProgress(user);
        userProgress.setTotalPoints(1000);
        userProgress.setCurrentLevel(1);

        boolean result = userProgress.isUserLevelUpping(1000);

        assertTrue(result);
    }

    @Test
    @DisplayName("Deve retornar false quando pontos insuficientes para level up")
    void deveRetornarFalseQuandoPontosInsuficientesParaLevelUp() {
        UserProgress userProgress = new UserProgress(user);
        userProgress.setTotalPoints(100);
        userProgress.setCurrentLevel(1);

        boolean result = userProgress.isUserLevelUpping(100);

        assertFalse(result);
    }

    @Test
    @DisplayName("Deve calcular pontos excedentes corretamente para level up")
    void deveCalcularPontosExcedentesCorretamente() {
        UserProgress userProgress = new UserProgress(user);
        userProgress.setTotalPoints(1000);
        userProgress.setCurrentLevel(1);
        var levelUpPoints = platform.calculateLevelPoints(2);

        Integer surplus = userProgress.getSurplusPointsLevelUp(1000);

        Integer expectedSurplus = (1000 + 1000) - levelUpPoints;
        assertEquals(expectedSurplus, surplus);
    }

    @Test
    @DisplayName("Deve redefinir totalPoints para zero")
    void deveRedefinirTotalPointsParaZero() {
        UserProgress userProgress = new UserProgress(user);
        userProgress.setTotalPoints(500);

        userProgress.redefineTotalPoints();

        assertEquals(0, userProgress.getTotalPoints());
    }

    @Test
    @DisplayName("Deve aumentar level corretamente")
    void deveAumentarLevelCorretamente() {
        UserProgress userProgress = new UserProgress(user);
        userProgress.setCurrentLevel(1);

        userProgress.increaseLevel();

        assertEquals(2, userProgress.getCurrentLevel());
    }

    @Test
    @DisplayName("Deve aumentar level múltiplas vezes")
    void deveAumentarLevelMultiplasVezes() {
        UserProgress userProgress = new UserProgress(user);
        userProgress.setCurrentLevel(1);

        userProgress.increaseLevel();
        userProgress.increaseLevel();
        userProgress.increaseLevel();

        assertEquals(4, userProgress.getCurrentLevel());
    }

    @Test
    @DisplayName("Deve retornar true quando pontos exatamente suficientes para level up")
    void deveRetornarTrueQuandoPontosExatamenteSuficientes() {
        UserProgress userProgress = new UserProgress(user);
        userProgress.setTotalPoints(0);
        userProgress.setCurrentLevel(1);
        var levelUpPoints = platform.calculateLevelPoints(2);

        boolean result = userProgress.isUserLevelUpping(levelUpPoints);

        assertTrue(result);
    }

    @Test
    @DisplayName("Deve calcular level up para diferentes níveis")
    void deveCalcularLevelUpParaDiferentesNiveis() {
        UserProgress userProgress = new UserProgress(user);
        userProgress.setCurrentLevel(2);
        userProgress.setTotalPoints(0);

        boolean result = userProgress.isUserLevelUpping(100);

        assertFalse(result);
    }
}


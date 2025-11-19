package io.github.thallesyan.gamification_api.domain.entities.progress;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.ProgressStatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para UserMissionProgress")
class UserMissionProgressTest {

    private UserMissionProgress userMissionProgress;
    private List<UserMissionGoalProgress> userGoalsProgress;
    private User user;
    private Mission mission;

    @BeforeEach
    void setUp() {
        user = new User();
        mission = new Mission();

        userMissionProgress = new UserMissionProgress();
        userMissionProgress.setUser(user);
        userMissionProgress.setMission(mission);

        userGoalsProgress = new ArrayList<>();
        userMissionProgress.setUserGoalsProgress(userGoalsProgress);
    }

    @Test
    @DisplayName("Deve retornar goal progress por ordem")
    void deveRetornarGoalProgressPorOrdem() {
        UserMissionGoalProgress goal1 = new UserMissionGoalProgress();
        UserMissionGoalProgress goal2 = new UserMissionGoalProgress();
        userGoalsProgress.addAll(Arrays.asList(goal1, goal2));

        UserMissionGoalProgress result = userMissionProgress.getGoalProgressByOrder(1);

        assertEquals(goal1, result);
    }

    @Test
    @DisplayName("Deve definir goal progress por ordem")
    void deveDefinirGoalProgressPorOrdem() {
        UserMissionGoalProgress goal1 = new UserMissionGoalProgress();
        UserMissionGoalProgress goal2 = new UserMissionGoalProgress();
        userGoalsProgress.addAll(Arrays.asList(goal1, goal2));

        UserMissionGoalProgress newGoal = new UserMissionGoalProgress();
        UserMissionGoalProgress result = userMissionProgress.setGoalProgressByOrder(newGoal, 2);

        assertEquals(newGoal, result);
        assertEquals(newGoal, userGoalsProgress.get(1));
    }

    @Test
    @DisplayName("Deve retornar current goal quando existir goal em progresso")
    void deveRetornarCurrentGoalQuandoExistirGoalEmProgresso() {
        UserMissionGoalProgress goal1 = new UserMissionGoalProgress();
        goal1.setStatus(ProgressStatusEnum.ASSIGNED);
        UserMissionGoalProgress goal2 = new UserMissionGoalProgress();
        goal2.setStatus(ProgressStatusEnum.IN_PROGRESS);
        userGoalsProgress.addAll(Arrays.asList(goal1, goal2));

        Optional<UserMissionGoalProgress> result = userMissionProgress.getCurrentGoal();

        assertTrue(result.isPresent());
        assertEquals(goal2, result.get());
    }

    @Test
    @DisplayName("Deve retornar Optional empty quando não existir current goal")
    void deveRetornarOptionalEmptyQuandoNaoExistirCurrentGoal() {
        UserMissionGoalProgress goal1 = new UserMissionGoalProgress();
        goal1.setStatus(ProgressStatusEnum.ASSIGNED);
        userGoalsProgress.add(goal1);

        Optional<UserMissionGoalProgress> result = userMissionProgress.getCurrentGoal();

        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Deve retornar next goal quando existir goal atribuído")
    void deveRetornarNextGoalQuandoExistirGoalAtribuido() {
        UserMissionGoalProgress goal1 = new UserMissionGoalProgress();
        goal1.setStatus(ProgressStatusEnum.COMPLETED);
        UserMissionGoalProgress goal2 = new UserMissionGoalProgress();
        goal2.setStatus(ProgressStatusEnum.ASSIGNED);
        userGoalsProgress.addAll(Arrays.asList(goal1, goal2));

        Optional<UserMissionGoalProgress> result = userMissionProgress.getNextGoal();

        assertTrue(result.isPresent());
        assertEquals(goal2, result.get());
    }

    @Test
    @DisplayName("Deve retornar Optional empty quando não existir next goal")
    void deveRetornarOptionalEmptyQuandoNaoExistirNextGoal() {
        UserMissionGoalProgress goal1 = new UserMissionGoalProgress();
        goal1.setStatus(ProgressStatusEnum.COMPLETED);
        userGoalsProgress.add(goal1);

        Optional<UserMissionGoalProgress> result = userMissionProgress.getNextGoal();

        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Deve retornar last goal")
    void deveRetornarLastGoal() {
        UserMissionGoalProgress goal1 = new UserMissionGoalProgress();
        UserMissionGoalProgress goal2 = new UserMissionGoalProgress();
        UserMissionGoalProgress goal3 = new UserMissionGoalProgress();
        userGoalsProgress.addAll(Arrays.asList(goal1, goal2, goal3));

        Optional<UserMissionGoalProgress> result = userMissionProgress.getLastGoal();

        assertTrue(result.isPresent());
        assertEquals(goal3, result.get());
    }

    @Test
    @DisplayName("Deve retornar Optional empty quando não existir goals")
    void deveRetornarOptionalEmptyQuandoNaoExistirGoals() {
        Optional<UserMissionGoalProgress> result = userMissionProgress.getLastGoal();

        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Deve contar goals completados corretamente")
    void deveContarGoalsCompletadosCorretamente() {
        UserMissionGoalProgress goal1 = new UserMissionGoalProgress();
        goal1.setStatus(ProgressStatusEnum.COMPLETED);
        UserMissionGoalProgress goal2 = new UserMissionGoalProgress();
        goal2.setStatus(ProgressStatusEnum.COMPLETED);
        UserMissionGoalProgress goal3 = new UserMissionGoalProgress();
        goal3.setStatus(ProgressStatusEnum.IN_PROGRESS);
        userGoalsProgress.addAll(Arrays.asList(goal1, goal2, goal3));

        Long count = userMissionProgress.getCountGoalsCompleted();

        assertEquals(2L, count);
    }

    @Test
    @DisplayName("Deve retornar zero quando nenhum goal estiver completado")
    void deveRetornarZeroQuandoNenhumGoalEstiverCompletado() {
        UserMissionGoalProgress goal1 = new UserMissionGoalProgress();
        goal1.setStatus(ProgressStatusEnum.IN_PROGRESS);
        userGoalsProgress.add(goal1);

        Long count = userMissionProgress.getCountGoalsCompleted();

        assertEquals(0L, count);
    }

    @Test
    @DisplayName("Deve retornar completion duration quando status for COMPLETED")
    void deveRetornarCompletionDurationQuandoStatusForCompleted() {
        userMissionProgress.setStatus(ProgressStatusEnum.COMPLETED);
        userMissionProgress.setStartDate(java.time.LocalDateTime.now().minusHours(2));
        userMissionProgress.setCompletionDate(java.time.LocalDateTime.now());

        Duration duration = userMissionProgress.getCompletionDuration();

        assertNotNull(duration);
        assertTrue(duration.toHours() >= 1);
        assertTrue(duration.toHours() <= 3);
    }

    @Test
    @DisplayName("Deve retornar null quando status não for COMPLETED")
    void deveRetornarNullQuandoStatusNaoForCompleted() {
        userMissionProgress.setStatus(ProgressStatusEnum.IN_PROGRESS);

        Duration duration = userMissionProgress.getCompletionDuration();

        assertNull(duration);
    }
}


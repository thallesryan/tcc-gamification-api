package io.github.thallesyan.gamification_api;


import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionGoalProgress;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.domain.services.impl.CalculateProgress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CalculateProgressTest {

    private UserMissionProgress userMissionProgress;

    @BeforeEach
    void setUp() {
        userMissionProgress = Mockito.mock(UserMissionProgress.class);
    }

    @Test
    void deveRetornarZeroQuandoNaoHaMetas() {
        when(userMissionProgress.getUserGoalsProgress()).thenReturn(List.of());
        when(userMissionProgress.getCountGoalsCompleted()).thenReturn(0L);

        Double result = CalculateProgress.missionProgress(userMissionProgress);

        assertEquals(0.0, result);
        verify(userMissionProgress).getUserGoalsProgress();
        verify(userMissionProgress).getCountGoalsCompleted();
    }

    @Test
    void deveRetornarZeroQuandoMetasCompletasEhZero() {
        List<UserMissionGoalProgress> missions = List.of(new UserMissionGoalProgress());

        when(userMissionProgress.getUserGoalsProgress()).thenReturn(missions);
        when(userMissionProgress.getCountGoalsCompleted()).thenReturn(0L);

        Double result = CalculateProgress.missionProgress(userMissionProgress);

        assertEquals(0.0, result);
    }

    @Test
    void deveCalcularProgressoCorretoEmPorcentagem() {
        List<UserMissionGoalProgress> missionsGoal =
                List.of(new UserMissionGoalProgress(),
                        new UserMissionGoalProgress(),
                        new UserMissionGoalProgress(),
                        new UserMissionGoalProgress(),
                        new UserMissionGoalProgress()
                );
        when(userMissionProgress.getUserGoalsProgress()).thenReturn(missionsGoal);
        when(userMissionProgress.getCountGoalsCompleted()).thenReturn(2L);

        Double result = CalculateProgress.missionProgress(userMissionProgress);

        // (2 / 5) * 100 = 40.0
        assertEquals(40.0, result);
    }

}


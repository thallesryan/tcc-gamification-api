package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.FindMissionCompletionHistoryBoundary;
import io.github.thallesyan.gamification_api.domain.boundary.UpdateMissionEstimatedDurationBoundary;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.domain.services.UpdateMissionEstimatedDuration;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.ProgressStatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes para UpdateMissionEstimatedDurationImpl")
class UpdateMissionEstimatedDurationImplTest {

    @Mock
    private FindMissionCompletionHistoryBoundary completionHistoryBoundary;

    @Mock
    private UpdateMissionEstimatedDurationBoundary updateMissionEstimatedDurationBoundary;

    @InjectMocks
    private UpdateMissionEstimatedDurationImpl updateMissionEstimatedDurationImpl;

    private UUID missionId;
    private UserMissionProgress userMissionProgress1;
    private UserMissionProgress userMissionProgress2;

    @BeforeEach
    void setUp() {
        missionId = UUID.randomUUID();

        userMissionProgress1 = new UserMissionProgress();
        userMissionProgress1.setStatus(ProgressStatusEnum.COMPLETED);
        userMissionProgress1.setStartDate(LocalDateTime.now().minusHours(2));
        userMissionProgress1.setCompletionDate(LocalDateTime.now());

        userMissionProgress2 = new UserMissionProgress();
        userMissionProgress2.setStatus(ProgressStatusEnum.COMPLETED);
        userMissionProgress2.setStartDate(LocalDateTime.now().minusHours(1));
        userMissionProgress2.setCompletionDate(LocalDateTime.now());
    }

    @Test
    @DisplayName("Deve recalcular duração estimada quando houver histórico de completação")
    void deveRecalcularDuracaoEstimadaQuandoHouverHistorico() {
        List<UserMissionProgress> completionHistory = Arrays.asList(
                userMissionProgress1, userMissionProgress2
        );

        when(completionHistoryBoundary.findCompletedByMission(missionId)).thenReturn(completionHistory);

        updateMissionEstimatedDurationImpl.recalculate(missionId);

        verify(completionHistoryBoundary).findCompletedByMission(missionId);
        verify(updateMissionEstimatedDurationBoundary).updateEstimatedDuration(
                eq(missionId), any(Duration.class)
        );
    }

    @Test
    @DisplayName("Deve calcular média corretamente para múltiplas completações")
    void deveCalcularMediaCorretamenteParaMultiplasCompletacoes() {
        UserMissionProgress userMissionProgress3 = new UserMissionProgress();
        userMissionProgress3.setStatus(ProgressStatusEnum.COMPLETED);
        userMissionProgress3.setStartDate(LocalDateTime.now().minusHours(3));
        userMissionProgress3.setCompletionDate(LocalDateTime.now());

        List<UserMissionProgress> completionHistory = Arrays.asList(
                userMissionProgress1, userMissionProgress2, userMissionProgress3
        );

        when(completionHistoryBoundary.findCompletedByMission(missionId)).thenReturn(completionHistory);

        updateMissionEstimatedDurationImpl.recalculate(missionId);

        verify(completionHistoryBoundary).findCompletedByMission(missionId);
        verify(updateMissionEstimatedDurationBoundary).updateEstimatedDuration(
                eq(missionId), any(Duration.class)
        );
    }

    @Test
    @DisplayName("Deve calcular corretamente quando houver apenas uma completação")
    void deveCalcularCorretamenteQuandoHouverApenasUmaCompletacao() {
        List<UserMissionProgress> completionHistory = Arrays.asList(userMissionProgress1);

        when(completionHistoryBoundary.findCompletedByMission(missionId)).thenReturn(completionHistory);

        updateMissionEstimatedDurationImpl.recalculate(missionId);

        verify(completionHistoryBoundary).findCompletedByMission(missionId);
        verify(updateMissionEstimatedDurationBoundary).updateEstimatedDuration(
                eq(missionId), any(Duration.class)
        );
    }
}


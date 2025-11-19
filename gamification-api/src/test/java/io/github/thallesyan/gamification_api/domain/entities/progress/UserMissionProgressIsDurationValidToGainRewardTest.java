package io.github.thallesyan.gamification_api.domain.entities.progress;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Rule;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.ProgressStatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para UserMissionProgress.isDurationValidToGainReward")
class UserMissionProgressIsDurationValidToGainRewardTest {

    private UserMissionProgress userMissionProgress;
    private Rule rule;
    private Mission mission;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        mission = new Mission();
        rule = new Rule();
        rule.setDurationLimit(Duration.ofHours(2));
        mission.setRule(rule);

        userMissionProgress = new UserMissionProgress();
        userMissionProgress.setMission(mission);
        userMissionProgress.setUser(user);
        userMissionProgress.setStatus(ProgressStatusEnum.COMPLETED);
    }

    @Test
    @DisplayName("Deve retornar true quando duração de conclusão for menor que o limite da regra")
    void deveRetornarTrueQuandoDuracaoForMenorQueOLimite() {
        LocalDateTime startDate = LocalDateTime.now().minusHours(1);
        LocalDateTime completionDate = LocalDateTime.now();
        userMissionProgress.setStartDate(startDate);
        userMissionProgress.setCompletionDate(completionDate);
        
        boolean result = userMissionProgress.isDurationValidToGainReward(rule);

        assertTrue(result, "Deveria retornar true quando duração é menor que o limite");
    }

    @Test
    @DisplayName("Deve retornar true quando duração de conclusão for igual ao limite da regra")
    void deveRetornarTrueQuandoDuracaoForIgualAOLimite() {
        LocalDateTime startDate = LocalDateTime.now().minusHours(2);
        LocalDateTime completionDate = LocalDateTime.now();
        userMissionProgress.setStartDate(startDate);
        userMissionProgress.setCompletionDate(completionDate);
        
        boolean result = userMissionProgress.isDurationValidToGainReward(rule);

        assertTrue(result, "Deveria retornar true quando duração é igual ao limite");
    }

    @Test
    @DisplayName("Deve retornar false quando duração de conclusão for maior que o limite da regra")
    void deveRetornarFalseQuandoDuracaoForMaiorQueOLimite() {
        LocalDateTime startDate = LocalDateTime.now().minusHours(3);
        LocalDateTime completionDate = LocalDateTime.now();
        userMissionProgress.setStartDate(startDate);
        userMissionProgress.setCompletionDate(completionDate);
        
        boolean result = userMissionProgress.isDurationValidToGainReward(rule);

        assertFalse(result, "Deveria retornar false quando duração é maior que o limite");
    }

    @Test
    @DisplayName("Deve retornar true quando duração for exatamente no limite (caso de borda)")
    void deveRetornarTrueQuandoDuracaoForExatamenteNoLimite() {
        Duration limitDuration = Duration.ofMinutes(90);
        rule.setDurationLimit(limitDuration);
        
        LocalDateTime startDate = LocalDateTime.now().minusMinutes(90);
        LocalDateTime completionDate = LocalDateTime.now();
        userMissionProgress.setStartDate(startDate);
        userMissionProgress.setCompletionDate(completionDate);
        
        boolean result = userMissionProgress.isDurationValidToGainReward(rule);

        assertTrue(result, "Deveria retornar true quando duração é exatamente igual ao limite");
    }

    @Test
    @DisplayName("Deve retornar false quando duração for ligeiramente maior que o limite")
    void deveRetornarFalseQuandoDuracaoForLigeiramenteMaiorQueOLimite() {
        Duration limitDuration = Duration.ofMinutes(120);
        rule.setDurationLimit(limitDuration);
        
        LocalDateTime startDate = LocalDateTime.now().minusMinutes(121);
        LocalDateTime completionDate = LocalDateTime.now();
        userMissionProgress.setStartDate(startDate);
        userMissionProgress.setCompletionDate(completionDate);
        
        boolean result = userMissionProgress.isDurationValidToGainReward(rule);

        assertFalse(result, "Deveria retornar false quando duração é ligeiramente maior que o limite");
    }

    @Test
    @DisplayName("Deve retornar true quando duração for ligeiramente menor que o limite")
    void deveRetornarTrueQuandoDuracaoForLigeiramenteMenorQueOLimite() {
        Duration limitDuration = Duration.ofMinutes(120);
        rule.setDurationLimit(limitDuration);
        
        LocalDateTime startDate = LocalDateTime.now().minusMinutes(119);
        LocalDateTime completionDate = LocalDateTime.now();
        userMissionProgress.setStartDate(startDate);
        userMissionProgress.setCompletionDate(completionDate);
        
        boolean result = userMissionProgress.isDurationValidToGainReward(rule);

        assertTrue(result, "Deveria retornar true quando duração é ligeiramente menor que o limite");
    }

    @Test
    @DisplayName("Deve retornar true quando duração for muito menor que o limite")
    void deveRetornarTrueQuandoDuracaoForMuitoMenorQueOLimite() {
        Duration limitDuration = Duration.ofHours(24);
        rule.setDurationLimit(limitDuration);
        
        LocalDateTime startDate = LocalDateTime.now().minusHours(1);
        LocalDateTime completionDate = LocalDateTime.now();
        userMissionProgress.setStartDate(startDate);
        userMissionProgress.setCompletionDate(completionDate);
        
        boolean result = userMissionProgress.isDurationValidToGainReward(rule);

        assertTrue(result, "Deveria retornar true quando duração é muito menor que o limite");
    }

    @Test
    @DisplayName("Deve retornar false quando duração for muito maior que o limite")
    void deveRetornarFalseQuandoDuracaoForMuitoMaiorQueOLimite() {
        Duration limitDuration = Duration.ofMinutes(30);
        rule.setDurationLimit(limitDuration);
        
        LocalDateTime startDate = LocalDateTime.now().minusHours(5);
        LocalDateTime completionDate = LocalDateTime.now();
        userMissionProgress.setStartDate(startDate);
        userMissionProgress.setCompletionDate(completionDate);
        
        boolean result = userMissionProgress.isDurationValidToGainReward(rule);

        assertFalse(result, "Deveria retornar false quando duração é muito maior que o limite");
    }

    @Test
    @DisplayName("Deve retornar true quando duração for zero (concluído instantaneamente)")
    void deveRetornarTrueQuandoDuracaoForZero() {
        Duration limitDuration = Duration.ofMinutes(1);
        rule.setDurationLimit(limitDuration);
        
        LocalDateTime now = LocalDateTime.now();
        userMissionProgress.setStartDate(now);
        userMissionProgress.setCompletionDate(now);
        
        boolean result = userMissionProgress.isDurationValidToGainReward(rule);

        assertTrue(result, "Deveria retornar true quando duração é zero (concluído instantaneamente)");
    }

    @Test
    @DisplayName("Deve lançar NullPointerException quando getCompletionDuration retornar null")
    void deveLancarNullPointerExceptionQuandoGetCompletionDurationRetornarNull() {
        userMissionProgress.setStatus(ProgressStatusEnum.IN_PROGRESS);
        
        assertThrows(NullPointerException.class, () -> {
            userMissionProgress.isDurationValidToGainReward(rule);
        }, "Deveria lançar NullPointerException quando getCompletionDuration retorna null");
    }

    @Test
    @DisplayName("Deve funcionar corretamente com diferentes unidades de tempo (segundos)")
    void deveFuncionarCorretamenteComSegundos() {
        Duration limitDuration = Duration.ofSeconds(3600);
        rule.setDurationLimit(limitDuration);
        
        LocalDateTime startDate = LocalDateTime.now().minusSeconds(1800);
        LocalDateTime completionDate = LocalDateTime.now();
        userMissionProgress.setStartDate(startDate);
        userMissionProgress.setCompletionDate(completionDate);
        
        boolean result = userMissionProgress.isDurationValidToGainReward(rule);

        assertTrue(result, "Deveria retornar true quando duração em segundos é menor que o limite");
    }

    @Test
    @DisplayName("Deve funcionar corretamente com diferentes unidades de tempo (dias)")
    void deveFuncionarCorretamenteComDias() {
        Duration limitDuration = Duration.ofDays(7);
        rule.setDurationLimit(limitDuration);
        
        LocalDateTime startDate = LocalDateTime.now().minusDays(3);
        LocalDateTime completionDate = LocalDateTime.now();
        userMissionProgress.setStartDate(startDate);
        userMissionProgress.setCompletionDate(completionDate);
        
        boolean result = userMissionProgress.isDurationValidToGainReward(rule);

        assertTrue(result, "Deveria retornar true quando duração em dias é menor que o limite");
    }
}


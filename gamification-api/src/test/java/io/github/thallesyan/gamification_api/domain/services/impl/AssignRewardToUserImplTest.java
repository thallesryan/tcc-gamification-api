package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.application.usecases.UserProgressPoints;
import io.github.thallesyan.gamification_api.domain.boundary.AssignRewardToUserBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Reward;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Rule;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserReward;
import io.github.thallesyan.gamification_api.domain.services.FindUserMissionById;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes para AssignRewardToUserImpl")
class AssignRewardToUserImplTest {

    @Mock
    private AssignRewardToUserBoundary assignRewardToUserBoundary;

    @Mock
    private UserProgressPoints userProgressPoints;

    @Mock
    private FindUserMissionById findUserMissionById;

    @InjectMocks
    private AssignRewardToUserImpl assignRewardToUserImpl;

    private UserMissionProgress userMissionProgress;
    private Mission mission;
    private Reward reward;
    private Rule rule;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        Platform platform = new Platform("test-platform", 1000, 0.75);
        user.setPlatform(platform);

        rule = new Rule();
        rule.setDurationLimit(Duration.ofHours(2));

        reward = new Reward();
        reward.setPoints(100);

        mission = new Mission();
        mission.setReward(reward);
        mission.setRule(rule);

        userMissionProgress = new UserMissionProgress();
        userMissionProgress.setId(1);
        userMissionProgress.setUser(user);
        userMissionProgress.setMission(mission);
        userMissionProgress.setStatus(ProgressStatusEnum.COMPLETED);
        userMissionProgress.setStartDate(LocalDateTime.now().minusHours(1));
        userMissionProgress.setCompletionDate(LocalDateTime.now());
    }

    @Test
    @DisplayName("Deve atribuir reward quando duração válida")
    void deveAtribuirRewardQuandoDuracaoValida() {
        when(findUserMissionById.byId(1)).thenReturn(userMissionProgress);
        UserReward userReward = new UserReward(user, reward);
        when(assignRewardToUserBoundary.assignReward(any(UserReward.class))).thenReturn(userReward);

        assignRewardToUserImpl.assignReward(1);

        verify(findUserMissionById).byId(1);
        verify(assignRewardToUserBoundary).assignReward(any(UserReward.class));
        verify(userProgressPoints).addRewardPoints(userReward);
    }

    @Test
    @DisplayName("Não deve atribuir reward quando reward for null")
    void naoDeveAtribuirRewardQuandoRewardForNull() {
        mission.setReward(null);
        when(findUserMissionById.byId(1)).thenReturn(userMissionProgress);

        assignRewardToUserImpl.assignReward(1);

        verify(findUserMissionById).byId(1);
        verify(assignRewardToUserBoundary, never()).assignReward(any());
        verify(userProgressPoints, never()).addRewardPoints(any());
    }

    @Test
    @DisplayName("Não deve atribuir reward quando rule for null")
    void naoDeveAtribuirRewardQuandoRuleForNull() {
        mission.setRule(null);
        when(findUserMissionById.byId(1)).thenReturn(userMissionProgress);

        assignRewardToUserImpl.assignReward(1);

        verify(findUserMissionById).byId(1);
        verify(assignRewardToUserBoundary, never()).assignReward(any());
        verify(userProgressPoints, never()).addRewardPoints(any());
    }

    @Test
    @DisplayName("Não deve atribuir reward quando duração inválida")
    void naoDeveAtribuirRewardQuandoDuracaoInvalida() {
        userMissionProgress.setStartDate(LocalDateTime.now().minusHours(3));
        userMissionProgress.setCompletionDate(LocalDateTime.now());
        when(findUserMissionById.byId(1)).thenReturn(userMissionProgress);

        assignRewardToUserImpl.assignReward(1);

        verify(findUserMissionById).byId(1);
        verify(assignRewardToUserBoundary, never()).assignReward(any());
        verify(userProgressPoints, never()).addRewardPoints(any());
    }

    @Test
    @DisplayName("Deve atribuir reward quando duração exatamente no limite")
    void deveAtribuirRewardQuandoDuracaoExatamenteNoLimite() {
        userMissionProgress.setStartDate(LocalDateTime.now().minusHours(2));
        userMissionProgress.setCompletionDate(LocalDateTime.now());
        when(findUserMissionById.byId(1)).thenReturn(userMissionProgress);
        UserReward userReward = new UserReward(user, reward);
        when(assignRewardToUserBoundary.assignReward(any(UserReward.class))).thenReturn(userReward);

        assignRewardToUserImpl.assignReward(1);

        verify(findUserMissionById).byId(1);
        verify(assignRewardToUserBoundary).assignReward(any(UserReward.class));
        verify(userProgressPoints).addRewardPoints(userReward);
    }
}


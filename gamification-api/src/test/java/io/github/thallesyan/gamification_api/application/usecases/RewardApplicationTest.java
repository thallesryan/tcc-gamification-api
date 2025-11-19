package io.github.thallesyan.gamification_api.application.usecases;

import io.github.thallesyan.gamification_api.application.exceptions.EntityNotFoundException;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Reward;
import io.github.thallesyan.gamification_api.domain.entities.reward.Badge;
import io.github.thallesyan.gamification_api.domain.services.CreateReward;
import io.github.thallesyan.gamification_api.domain.services.FindBadge;
import io.github.thallesyan.gamification_api.domain.services.FindRewards;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes para RewardApplication")
class RewardApplicationTest {

    @Mock
    private CreateReward createReward;

    @Mock
    private FindRewards findRewards;

    @Mock
    private FindBadge findBadge;

    @InjectMocks
    private RewardApplication rewardApplication;

    private Reward reward;
    private Badge badge;

    @BeforeEach
    void setUp() {
        reward = new Reward();
        reward.setPoints(100);

        badge = new Badge();
        badge.setIdentifier(UUID.randomUUID());
        reward.setBadge(badge);
    }

    @Test
    @DisplayName("Deve criar reward quando badge não tiver identifier")
    void deveCriarRewardQuandoBadgeNaoTiverIdentifier() {
        reward.getBadge().setIdentifier(null);
        when(createReward.createReward(any(Reward.class))).thenReturn(reward);

        Reward result = rewardApplication.createReward(reward);

        assertNotNull(result);
        verify(createReward).createReward(reward);
        verify(findBadge, never()).findBadgeById(any());
    }

    @Test
    @DisplayName("Deve criar reward quando badge tiver identifier e existir no banco")
    void deveCriarRewardQuandoBadgeTiverIdentifierEExistir() {
        Badge foundBadge = new Badge();
        foundBadge.setIdentifier(badge.getIdentifier());
        when(findBadge.findBadgeById(badge.getIdentifier())).thenReturn(Optional.of(foundBadge));
        when(createReward.createReward(any(Reward.class))).thenReturn(reward);

        Reward result = rewardApplication.createReward(reward);

        assertNotNull(result);
        verify(findBadge).findBadgeById(badge.getIdentifier());
        verify(createReward).createReward(reward);
    }

    @Test
    @DisplayName("Deve lançar EntityNotFoundException quando badge não existir")
    void deveLancarEntityNotFoundExceptionQuandoBadgeNaoExistir() {
        when(findBadge.findBadgeById(badge.getIdentifier())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            rewardApplication.createReward(reward);
        });

        verify(findBadge).findBadgeById(badge.getIdentifier());
        verify(createReward, never()).createReward(any());
    }

    @Test
    @DisplayName("Deve buscar todos os rewards")
    void deveBuscarTodosOsRewards() {
        rewardApplication.findAllRewards();

        verify(findRewards).findAllRewards();
    }

    @Test
    @DisplayName("Deve buscar reward por ID")
    void deveBuscarRewardPorId() {
        UUID id = UUID.randomUUID();
        rewardApplication.findRewardById(id);

        verify(findRewards).findRewardById(id);
    }
}


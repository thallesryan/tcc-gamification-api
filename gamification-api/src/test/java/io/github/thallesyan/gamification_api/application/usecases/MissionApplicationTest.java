package io.github.thallesyan.gamification_api.application.usecases;

import io.github.thallesyan.gamification_api.application.exceptions.EntityNotFoundException;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Reward;
import io.github.thallesyan.gamification_api.domain.services.CreateMission;
import io.github.thallesyan.gamification_api.domain.services.FindMission;
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
@DisplayName("Testes para MissionApplication")
class MissionApplicationTest {

    @Mock
    private CreateMission createMission;

    @Mock
    private FindMission findMission;

    @Mock
    private FindRewards findRewards;

    @InjectMocks
    private MissionApplication missionApplication;

    private Mission mission;
    private Reward reward;

    @BeforeEach
    void setUp() {
        mission = new Mission();
        reward = new Reward();
        reward.setIdentifier(UUID.randomUUID());
        mission.setReward(reward);
    }

    @Test
    @DisplayName("Deve criar mission quando reward não existir")
    void deveCriarMissionQuandoRewardNaoExistir() {
        mission.setReward(null);
        when(createMission.createMission(any(Mission.class))).thenReturn(mission);

        Mission result = missionApplication.createMission(mission);

        assertNotNull(result);
        verify(createMission).createMission(mission);
        verify(findRewards, never()).findRewardById(any());
    }

    @Test
    @DisplayName("Deve criar mission quando reward não tiver identifier")
    void deveCriarMissionQuandoRewardNaoTiverIdentifier() {
        reward.setIdentifier(null);
        when(createMission.createMission(any(Mission.class))).thenReturn(mission);

        Mission result = missionApplication.createMission(mission);

        assertNotNull(result);
        verify(createMission).createMission(mission);
        verify(findRewards, never()).findRewardById(any());
    }

    @Test
    @DisplayName("Deve criar mission quando reward existir")
    void deveCriarMissionQuandoRewardExistir() {
        when(findRewards.findRewardById(reward.getIdentifier())).thenReturn(Optional.of(reward));
        when(createMission.createMission(any(Mission.class))).thenReturn(mission);

        Mission result = missionApplication.createMission(mission);

        assertNotNull(result);
        verify(findRewards).findRewardById(reward.getIdentifier());
        verify(createMission).createMission(mission);
    }

    @Test
    @DisplayName("Deve lançar EntityNotFoundException quando reward não existir")
    void deveLancarEntityNotFoundExceptionQuandoRewardNaoExistir() {
        when(findRewards.findRewardById(reward.getIdentifier())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            missionApplication.createMission(mission);
        });

        verify(findRewards).findRewardById(reward.getIdentifier());
        verify(createMission, never()).createMission(any());
    }

    @Test
    @DisplayName("Deve buscar mission por ID")
    void deveBuscarMissionPorId() {
        UUID id = UUID.randomUUID();
        missionApplication.findById(id);

        verify(findMission).byMissionId(id);
    }
}


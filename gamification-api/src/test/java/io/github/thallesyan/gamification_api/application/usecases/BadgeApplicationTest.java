package io.github.thallesyan.gamification_api.application.usecases;

import io.github.thallesyan.gamification_api.application.exceptions.EntityNotFoundException;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.entities.reward.Badge;
import io.github.thallesyan.gamification_api.domain.entities.reward.Rarity;
import io.github.thallesyan.gamification_api.domain.entities.reward.RarityEnum;
import io.github.thallesyan.gamification_api.domain.services.CreateBadge;
import io.github.thallesyan.gamification_api.domain.services.FindBadgesByRarity;
import io.github.thallesyan.gamification_api.domain.services.FindRarity;
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
@DisplayName("Testes para BadgeApplication")
class BadgeApplicationTest {

    @Mock
    private CreateBadge createBadge;

    @Mock
    private FindBadgesByRarity findBadgesByRarity;

    @Mock
    private FindRarity findRarity;

    @InjectMocks
    private BadgeApplication badgeApplication;

    private Badge badge;
    private Rarity rarity;
    private Platform platform;

    @BeforeEach
    void setUp() {
        platform = new Platform("test-platform", 1000, 0.75);
        rarity = new Rarity();
        rarity.setValue(RarityEnum.COMMON);
        rarity.setPlatform(platform);
        rarity.setPoints(50);

        badge = new Badge();
        badge.setRarity(rarity);
    }

    @Test
    @DisplayName("Deve criar badge quando rarity existir")
    void deveCriarBadgeQuandoRarityExistir() {
        when(findRarity.findByRarityEnumAndPlatform(RarityEnum.COMMON, platform))
                .thenReturn(Optional.of(rarity));
        when(createBadge.createBadge(any(Badge.class))).thenReturn(badge);

        Badge result = badgeApplication.createBadge(badge);

        assertNotNull(result);
        verify(findRarity).findByRarityEnumAndPlatform(RarityEnum.COMMON, platform);
        verify(createBadge).createBadge(badge);
    }

    @Test
    @DisplayName("Deve lançar EntityNotFoundException quando rarity não existir")
    void deveLancarEntityNotFoundExceptionQuandoRarityNaoExistir() {
        when(findRarity.findByRarityEnumAndPlatform(RarityEnum.COMMON, platform))
                .thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            badgeApplication.createBadge(badge);
        });

        verify(findRarity).findByRarityEnumAndPlatform(RarityEnum.COMMON, platform);
        verify(createBadge, never()).createBadge(any());
    }

    @Test
    @DisplayName("Deve buscar badges por rarity")
    void deveBuscarBadgesPorRarity() {
        badgeApplication.findBadgesByRarity(RarityEnum.COMMON, platform);

        verify(findBadgesByRarity).findBadgesByRarity(RarityEnum.COMMON, platform);
    }
}


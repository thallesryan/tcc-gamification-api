package io.github.thallesyan.gamification_api.application.usecases;

import io.github.thallesyan.gamification_api.application.exceptions.PlatformNotFoundException;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.entities.reward.Rarity;
import io.github.thallesyan.gamification_api.domain.entities.reward.RarityEnum;
import io.github.thallesyan.gamification_api.domain.services.AssociatePoints;
import io.github.thallesyan.gamification_api.domain.services.FindRarity;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.PlatformJpaPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes para RarityApplication")
class RarityApplicationTest {

    @Mock
    private AssociatePoints associatePoints;

    @Mock
    private FindRarity findRarity;

    @Mock
    private PlatformJpaPersistence platformJpaPersistence;

    @InjectMocks
    private RarityApplication rarityApplication;

    private Platform platform;
    private Rarity rarity;

    @BeforeEach
    void setUp() {
        platform = new Platform("test-platform", 1000, 0.75);
        rarity = new Rarity();
        rarity.setValue(RarityEnum.COMMON);
        rarity.setPlatform(platform);
        rarity.setPoints(50);
    }

    @Test
    @DisplayName("Deve associar pontos a uma rarity")
    void deveAssociarPontosAUmaRarity() {
        when(associatePoints.associatePoints(any(Rarity.class))).thenReturn(rarity);

        Rarity result = rarityApplication.associatePoints(RarityEnum.COMMON, 50, platform);

        assertNotNull(result);
        verify(associatePoints).associatePoints(any(Rarity.class));
    }

    @Test
    @DisplayName("Deve associar pontos a uma lista de rarities quando plataformas existirem")
    void deveAssociarPontosAUmaListaDeRaritiesQuandoPlataformasExistirem() {
        Rarity rarity2 = new Rarity();
        rarity2.setValue(RarityEnum.RARE);
        rarity2.setPlatform(platform);
        rarity2.setPoints(100);

        List<Rarity> rarities = Arrays.asList(rarity, rarity2);

        when(platformJpaPersistence.existsById("test-platform")).thenReturn(true);
        when(associatePoints.associatePoints(any(Rarity.class))).thenReturn(rarity);

        rarityApplication.associatePointsList(rarities);

        verify(platformJpaPersistence, times(1)).existsById("test-platform");
        verify(associatePoints, times(2)).associatePoints(any(Rarity.class));
    }

    @Test
    @DisplayName("Deve lançar PlatformNotFoundException quando plataforma não existir")
    void deveLancarPlatformNotFoundExceptionQuandoPlataformaNaoExistir() {
        List<Rarity> rarities = Arrays.asList(rarity);

        when(platformJpaPersistence.existsById("test-platform")).thenReturn(false);

        assertThrows(PlatformNotFoundException.class, () -> {
            rarityApplication.associatePointsList(rarities);
        });

        verify(platformJpaPersistence).existsById("test-platform");
        verify(associatePoints, never()).associatePoints(any());
    }

    @Test
    @DisplayName("Deve buscar todas as rarities por plataforma")
    void deveBuscarTodasAsRaritiesPorPlataforma() {
        rarityApplication.getAllRaritiesByPlatform(platform);

        verify(findRarity).findByPlatform(platform);
    }

    @Test
    @DisplayName("Deve ignorar rarities com platform null ao validar")
    void deveIgnorarRaritiesComPlatformNullAoValidar() {
        Rarity rarityWithNullPlatform = new Rarity();
        rarityWithNullPlatform.setPlatform(null);

        List<Rarity> rarities = Arrays.asList(rarity, rarityWithNullPlatform);

        when(platformJpaPersistence.existsById("test-platform")).thenReturn(true);
        when(associatePoints.associatePoints(any(Rarity.class))).thenReturn(rarity);

        rarityApplication.associatePointsList(rarities);

        verify(platformJpaPersistence, times(1)).existsById("test-platform");
        verify(associatePoints, times(2)).associatePoints(any(Rarity.class));
    }
}


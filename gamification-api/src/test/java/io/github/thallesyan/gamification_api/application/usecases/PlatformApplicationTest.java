package io.github.thallesyan.gamification_api.application.usecases;

import io.github.thallesyan.gamification_api.application.exceptions.PlatformNotFoundException;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.services.CreatePlatform;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.PlatformJpaPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.PlatformJPA;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.PlatformPersistenceMapper;
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
@DisplayName("Testes para PlatformApplication")
class PlatformApplicationTest {

    @Mock
    private CreatePlatform createPlatform;

    @Mock
    private PlatformJpaPersistence platformJpaPersistence;

    @Mock
    private PlatformPersistenceMapper platformPersistenceMapper;

    @InjectMocks
    private PlatformApplication platformApplication;

    private Platform platform;
    private PlatformJPA platformJPA;

    @BeforeEach
    void setUp() {
        platform = new Platform("test-platform", 1000, 0.75);
        platformJPA = new PlatformJPA();
        platformJPA.setName("test-platform");
    }

    @Test
    @DisplayName("Deve criar plataforma")
    void deveCriarPlataforma() {
        when(createPlatform.createPlatform(any(Platform.class))).thenReturn(platform);

        Platform result = platformApplication.createPlatform(platform);

        assertNotNull(result);
        assertEquals(platform, result);
        verify(createPlatform).createPlatform(platform);
    }

    @Test
    @DisplayName("Deve buscar plataforma por nome quando existir")
    void deveBuscarPlataformaPorNomeQuandoExistir() {
        when(platformJpaPersistence.findById("test-platform")).thenReturn(Optional.of(platformJPA));
        when(platformPersistenceMapper.toModel(platformJPA)).thenReturn(platform);

        Platform result = platformApplication.findByName("test-platform");

        assertNotNull(result);
        assertEquals(platform, result);
        verify(platformJpaPersistence).findById("test-platform");
        verify(platformPersistenceMapper).toModel(platformJPA);
    }

    @Test
    @DisplayName("Deve lançar PlatformNotFoundException quando plataforma não existir")
    void deveLancarPlatformNotFoundExceptionQuandoPlataformaNaoExistir() {
        when(platformJpaPersistence.findById("non-existent-platform")).thenReturn(Optional.empty());

        assertThrows(PlatformNotFoundException.class, () -> {
            platformApplication.findByName("non-existent-platform");
        });

        verify(platformJpaPersistence).findById("non-existent-platform");
        verify(platformPersistenceMapper, never()).toModel(any());
    }
}


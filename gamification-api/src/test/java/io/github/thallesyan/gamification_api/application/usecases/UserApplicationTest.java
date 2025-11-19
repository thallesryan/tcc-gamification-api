package io.github.thallesyan.gamification_api.application.usecases;

import io.github.thallesyan.gamification_api.application.exceptions.UserNotFoundException;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserProgress;
import io.github.thallesyan.gamification_api.domain.services.CreateUserProgress;
import io.github.thallesyan.gamification_api.domain.services.FindUserByEmail;
import io.github.thallesyan.gamification_api.domain.services.FindUserProgress;
import io.github.thallesyan.gamification_api.domain.services.RegisterUser;
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
@DisplayName("Testes para UserApplication")
class UserApplicationTest {

    @Mock
    private RegisterUser registerUser;

    @Mock
    private FindUserByEmail findUserByEmail;

    @Mock
    private CreateUserProgress createUserProgress;

    @Mock
    private FindUserProgress findUserProgress;

    @Mock
    private PlatformApplication platformApplication;

    @InjectMocks
    private UserApplication userApplication;

    private User user;
    private Platform platform;
    private UserProgress userProgress;

    @BeforeEach
    void setUp() {
        platform = new Platform("test-platform", 1000, 0.75);
        user = new User();
        user.setPlatform(platform);
        userProgress = new UserProgress(user);
    }

    @Test
    @DisplayName("Deve registrar usuário e criar UserProgress")
    void deveRegistrarUsuarioECriarUserProgress() {
        when(platformApplication.findByName("test-platform")).thenReturn(platform);
        when(registerUser.registerUser(user)).thenReturn(user);
        when(createUserProgress.createUserProgress(any(UserProgress.class))).thenReturn(userProgress);

        User result = userApplication.registerUser(user);

        assertNotNull(result);
        verify(platformApplication).findByName("test-platform");
        verify(registerUser).registerUser(user);
        verify(createUserProgress).createUserProgress(any(UserProgress.class));
    }

    @Test
    @DisplayName("Deve buscar usuário por email")
    void deveBuscarUsuarioPorEmail() {
        when(findUserByEmail.byEmail("test@example.com", "test-platform"))
                .thenReturn(Optional.of(user));

        User result = userApplication.findUserByEmail("test@example.com", "test-platform");

        assertNotNull(result);
        assertEquals(user, result);
        verify(findUserByEmail).byEmail("test@example.com", "test-platform");
    }

    @Test
    @DisplayName("Deve lançar UserNotFoundException quando usuário não for encontrado por email")
    void deveLancarUserNotFoundExceptionQuandoUsuarioNaoForEncontrado() {
        when(findUserByEmail.byEmail("test@example.com", "test-platform"))
                .thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userApplication.findUserByEmail("test@example.com", "test-platform");
        });

        verify(findUserByEmail).byEmail("test@example.com", "test-platform");
    }

    @Test
    @DisplayName("Deve buscar UserProgress por email")
    void deveBuscarUserProgressPorEmail() {
        when(findUserByEmail.byEmail("test@example.com", "test-platform"))
                .thenReturn(Optional.of(user));
        when(findUserProgress.findByUser(user)).thenReturn(Optional.of(userProgress));

        Optional<UserProgress> result = userApplication.findUserProgressByEmail("test@example.com", "test-platform");

        assertTrue(result.isPresent());
        assertEquals(userProgress, result.get());
        verify(findUserByEmail).byEmail("test@example.com", "test-platform");
        verify(findUserProgress).findByUser(user);
    }

    @Test
    @DisplayName("Deve retornar Optional empty quando UserProgress não for encontrado")
    void deveRetornarOptionalEmptyQuandoUserProgressNaoForEncontrado() {
        when(findUserByEmail.byEmail("test@example.com", "test-platform"))
                .thenReturn(Optional.of(user));
        when(findUserProgress.findByUser(user)).thenReturn(Optional.empty());

        Optional<UserProgress> result = userApplication.findUserProgressByEmail("test@example.com", "test-platform");

        assertFalse(result.isPresent());
        verify(findUserByEmail).byEmail("test@example.com", "test-platform");
        verify(findUserProgress).findByUser(user);
    }
}


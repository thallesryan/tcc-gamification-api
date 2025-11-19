package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.RegisterUserBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.domain.exceptions.UserAlreadyExistsException;
import io.github.thallesyan.gamification_api.domain.services.RegisterUser;
import io.github.thallesyan.gamification_api.domain.services.VerifyUserExists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes para RegisterUserImpl")
class RegisterUserImplTest {

    @Mock
    private RegisterUserBoundary registerUserBoundary;

    @Mock
    private VerifyUserExists verifyUserExists;

    @InjectMocks
    private RegisterUserImpl registerUserImpl;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setEmail("test@example.com");
        user.setName("Test User");
        Platform platform = new Platform("test-platform", 1000, 0.75);
        user.setPlatform(platform);
    }

    @Test
    @DisplayName("Deve registrar usuário quando email não existir")
    void deveRegistrarUsuarioQuandoEmailNaoExistir() {
        when(verifyUserExists.byEmail("test@example.com")).thenReturn(false);
        when(registerUserBoundary.registerUser(any(User.class))).thenReturn(user);

        User result = registerUserImpl.registerUser(user);

        assertNotNull(result);
        assertEquals(user, result);
        verify(verifyUserExists).byEmail("test@example.com");
        verify(registerUserBoundary).registerUser(user);
    }

    @Test
    @DisplayName("Deve lançar UserAlreadyExistsException quando email já existir")
    void deveLancarUserAlreadyExistsExceptionQuandoEmailJaExistir() {
        when(verifyUserExists.byEmail("test@example.com")).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () -> {
            registerUserImpl.registerUser(user);
        });

        verify(verifyUserExists).byEmail("test@example.com");
        verify(registerUserBoundary, never()).registerUser(any());
    }
}


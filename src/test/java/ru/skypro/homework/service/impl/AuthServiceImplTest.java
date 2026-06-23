package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private UserDetailsManager manager;
    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    void register_shouldReturnFalseIfUserExists() {
        Register register = new Register();
        register.setUsername("existing@test.com");

        when(manager.userExists("existing@test.com")).thenReturn(true);

        boolean result = authService.register(register);

        assertFalse(result);
        verify(manager, never()).createUser(any());
    }

    @Test
    void register_shouldReturnTrueIfUserRegistered() {
        // Arrange (Подготовка)
        Register register = new Register();
        register.setUsername("new@test.com");
        register.setPassword("password");
        register.setRole(Role.USER);

        when(manager.userExists("new@test.com")).thenReturn(false);

        // ОБУЧАЕМ ЭНКОДЕР: когда вызывают encode с любой строкой, вернуть "encoded_password"
        when(encoder.encode(anyString())).thenReturn("encoded_password");

        // Act (Действие)
        boolean result = authService.register(register);

        // Assert (Проверка)
        assertTrue(result);
        verify(manager).createUser(any());
    }
}
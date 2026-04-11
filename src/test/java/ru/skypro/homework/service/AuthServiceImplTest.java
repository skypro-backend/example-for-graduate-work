package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.RoleEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.RoleRepository;
import ru.skypro.homework.repository.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты сервиса аутентификации")
class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    @DisplayName("login - успешный вход с валидными данными")
    void login_ValidCredentials_ReturnsTrue() {
        String email = "user@example.com";
        String password = "password123";
        String encodedPassword = "encodedPassword";

        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setPassword(encodedPassword);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(encoder.matches(password, encodedPassword)).thenReturn(true);

        boolean result = authService.login(email, password);

        assertThat(result).isTrue();
        verify(userRepository).findByEmail(email);
        verify(encoder).matches(password, encodedPassword);
    }

    @Test
    @DisplayName("login - ошибка при неверном пароле")
    void login_InvalidPassword_ReturnsFalse() {
        String email = "user@example.com";
        String password = "wrongpassword";
        String encodedPassword = "encodedPassword";

        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setPassword(encodedPassword);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(encoder.matches(password, encodedPassword)).thenReturn(false);

        boolean result = authService.login(email, password);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("login - пользователь не найден")
    void login_UserNotFound_ReturnsFalse() {
        String email = "nonexistent@example.com";
        String password = "password123";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        boolean result = authService.login(email, password);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("register - успешная регистрация нового пользователя")
    void register_NewUser_ReturnsTrue() {
        Register register = new Register();
        register.setUsername("newuser@example.com");
        register.setPassword("password123");
        register.setFirstName("Иван");
        register.setLastName("Иванов");
        register.setPhone("+79991234567");
        register.setRole(Role.USER);

        RoleEntity roleEntity = new RoleEntity(1, "USER");

        when(userRepository.findByEmail(register.getUsername())).thenReturn(Optional.empty());
        when(roleRepository.findByName("USER")).thenReturn(Optional.of(roleEntity));
        when(encoder.encode(register.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(UserEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        boolean result = authService.register(register);

        assertThat(result).isTrue();
        verify(userRepository).save(any(UserEntity.class));
    }

    @Test
    @DisplayName("register - ошибка при существующем пользователе")
    void register_ExistingUser_ReturnsFalse() {
        Register register = new Register();
        register.setUsername("existing@example.com");
        register.setPassword("password123");

        UserEntity existingUser = new UserEntity();
        existingUser.setEmail(register.getUsername());

        when(userRepository.findByEmail(register.getUsername())).thenReturn(Optional.of(existingUser));

        boolean result = authService.register(register);

        assertThat(result).isFalse();
        verify(userRepository, never()).save(any(UserEntity.class));
    }
}
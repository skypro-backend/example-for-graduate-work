package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.Role;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImpl authService;

    private UserEntity existingUser;
    private Register newRegister;

    @BeforeEach
    void setUp() {
        existingUser = new UserEntity();
        existingUser.setId(1);
        existingUser.setEmail("existing@test.com");
        existingUser.setPhone("+79991234567");
        existingUser.setPassword("$2a$10$hashedPassword"); // хешированный пароль

        newRegister = new Register();
        newRegister.setUsername("newuser@test.com");
        newRegister.setPassword("password");
        newRegister.setFirstName("Иван");
        newRegister.setLastName("Иванов");
        newRegister.setPhone("+78881234567");
        newRegister.setRole(Role.USER);
    }

    @Test
    void login_whenValidCredentials_shouldReturnTrue() {
        // Given
        when(userRepository.findByEmail("existing@test.com")).thenReturn(Optional.of(existingUser));
        when(passwordEncoder.matches("password", existingUser.getPassword())).thenReturn(true);

        // When
        boolean result = authService.login("existing@test.com", "password");

        // Then
        assertThat(result).isTrue();
        verify(userRepository).findByEmail("existing@test.com");
        verify(passwordEncoder).matches("password", existingUser.getPassword());
    }

    @Test
    void login_whenWrongPassword_shouldReturnFalse() {
        // Given
        when(userRepository.findByEmail("existing@test.com")).thenReturn(Optional.of(existingUser));
        when(passwordEncoder.matches("wrong", existingUser.getPassword())).thenReturn(false);

        // When
        boolean result = authService.login("existing@test.com", "wrong");

        // Then
        assertThat(result).isFalse();
    }

    @Test
    void login_whenUserNotFound_shouldReturnFalse() {
        // Given
        when(userRepository.findByEmail("unknown@test.com")).thenReturn(Optional.empty());

        // When
        boolean result = authService.login("unknown@test.com", "password");

        // Then
        assertThat(result).isFalse();
    }

    @Test
    void register_whenEmailExists_shouldReturnFalse() {
        // Given
        when(userRepository.findByEmail(newRegister.getUsername())).thenReturn(Optional.of(existingUser));

        // When
        boolean result = authService.register(newRegister);

        // Then
        assertThat(result).isFalse();
        verify(userRepository).findByEmail(newRegister.getUsername());
        verify(userRepository, never()).findByPhone(anyString());
        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    void register_whenPhoneExists_shouldReturnFalse() {
        // Given
        when(userRepository.findByEmail(newRegister.getUsername())).thenReturn(Optional.empty());
        when(userRepository.findByPhone(newRegister.getPhone())).thenReturn(Optional.of(existingUser));

        // When
        boolean result = authService.register(newRegister);

        // Then
        assertThat(result).isFalse();
        verify(userRepository).findByEmail(newRegister.getUsername());
        verify(userRepository).findByPhone(newRegister.getPhone());
        verify(userRepository, never()).save(any());
    }

    @Test
    void register_whenDataUnique_shouldSaveAndReturnTrue() {
        // Given
        when(userRepository.findByEmail(newRegister.getUsername())).thenReturn(Optional.empty());
        when(userRepository.findByPhone(newRegister.getPhone())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(newRegister.getPassword())).thenReturn("$2a$10$newHashedPassword");

        // When
        boolean result = authService.register(newRegister);

        // Then
        assertThat(result).isTrue();
        verify(userRepository).save(argThat(user -> user.getEmail().equals("newuser@test.com") && user.getFirstName().equals("Иван") && user.getLastName().equals("Иванов") && user.getPhone().equals("+78881234567") && user.getRole() == Role.USER && user.getPassword().equals("$2a$10$newHashedPassword")));
    }

    @Test
    void isPhoneExists_whenPhoneFound_shouldReturnTrue() {
        // Given
        when(userRepository.findByPhone("+79991234567")).thenReturn(Optional.of(existingUser));

        // When
        boolean result = authService.isPhoneExists("+79991234567");

        // Then
        assertThat(result).isTrue();
    }

    @Test
    void isPhoneExists_whenPhoneNotFound_shouldReturnFalse() {
        // Given
        when(userRepository.findByPhone("+78881234567")).thenReturn(Optional.empty());

        // When
        boolean result = authService.isPhoneExists("+78881234567");

        // Then
        assertThat(result).isFalse();
    }
}
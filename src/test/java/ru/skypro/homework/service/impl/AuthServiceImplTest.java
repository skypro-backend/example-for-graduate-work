package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.skypro.homework.dto.Login;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit-тесты сервиса регистрации и авторизации.
 */
@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        authService = new AuthServiceImpl(
                userRepository,
                userMapper,
                passwordEncoder
        );
    }

    @Test
    @DisplayName("Новый пользователь успешно регистрируется")
    void registerShouldSaveNewUser() {
        Register register = createRegister();

        UserEntity mappedUser = new UserEntity();

        when(userRepository.existsByUsernameIgnoreCase(
                register.getUsername()
        )).thenReturn(false);

        when(userMapper.toEntity(register))
                .thenReturn(mappedUser);

        when(passwordEncoder.encode(
                register.getPassword()
        )).thenReturn("encoded-password");

        boolean result = authService.register(register);

        assertTrue(result);

        ArgumentCaptor<UserEntity> userCaptor =
                ArgumentCaptor.forClass(UserEntity.class);

        verify(userRepository).save(
                userCaptor.capture()
        );

        UserEntity savedUser = userCaptor.getValue();

        assertEquals(
                "user@mail.ru",
                savedUser.getUsername()
        );

        assertEquals(
                "encoded-password",
                savedUser.getPassword()
        );

        verify(passwordEncoder)
                .encode("password123");
    }

    @Test
    @DisplayName("Пробелы вокруг username удаляются")
    void registerShouldTrimUsername() {
        Register register = createRegister();
        register.setUsername("  user@mail.ru  ");

        UserEntity mappedUser = new UserEntity();

        when(userRepository.existsByUsernameIgnoreCase(
                register.getUsername()
        )).thenReturn(false);

        when(userMapper.toEntity(register))
                .thenReturn(mappedUser);

        when(passwordEncoder.encode(
                register.getPassword()
        )).thenReturn("encoded-password");

        boolean result = authService.register(register);

        assertTrue(result);

        assertEquals(
                "user@mail.ru",
                mappedUser.getUsername()
        );

        verify(userRepository).save(mappedUser);
    }

    @Test
    @DisplayName("Повторная регистрация запрещена")
    void registerShouldReturnFalseWhenUsernameExists() {
        Register register = createRegister();

        when(userRepository.existsByUsernameIgnoreCase(
                register.getUsername()
        )).thenReturn(true);

        boolean result = authService.register(register);

        assertFalse(result);

        verify(userMapper, never())
                .toEntity(any());

        verify(passwordEncoder, never())
                .encode(anyString());

        verify(userRepository, never())
                .save(any());
    }

    @Test
    @DisplayName("Вход успешен при правильном пароле")
    void loginShouldReturnTrueWhenPasswordIsCorrect() {
        Login login = new Login(
                "user@mail.ru",
                "password123"
        );

        UserEntity user = new UserEntity();
        user.setUsername("user@mail.ru");
        user.setPassword("encoded-password");

        when(userRepository.findByUsernameIgnoreCase(
                login.getUsername()
        )).thenReturn(Optional.of(user));

        when(passwordEncoder.matches(
                login.getPassword(),
                user.getPassword()
        )).thenReturn(true);

        boolean result = authService.login(login);

        assertTrue(result);
    }

    @Test
    @DisplayName("Вход отклонён при неправильном пароле")
    void loginShouldReturnFalseWhenPasswordIsIncorrect() {
        Login login = new Login(
                "user@mail.ru",
                "wrong-password"
        );

        UserEntity user = new UserEntity();
        user.setUsername("user@mail.ru");
        user.setPassword("encoded-password");

        when(userRepository.findByUsernameIgnoreCase(
                login.getUsername()
        )).thenReturn(Optional.of(user));

        when(passwordEncoder.matches(
                login.getPassword(),
                user.getPassword()
        )).thenReturn(false);

        boolean result = authService.login(login);

        assertFalse(result);
    }

    @Test
    @DisplayName("Вход отклонён, если пользователь не найден")
    void loginShouldReturnFalseWhenUserDoesNotExist() {
        Login login = new Login(
                "unknown@mail.ru",
                "password123"
        );

        when(userRepository.findByUsernameIgnoreCase(
                login.getUsername()
        )).thenReturn(Optional.empty());

        boolean result = authService.login(login);

        assertFalse(result);

        verify(passwordEncoder, never())
                .matches(anyString(), anyString());
    }

    private Register createRegister() {
        return new Register(
                "user@mail.ru",
                "password123",
                "Юрий",
                "Ермилов",
                "+7(999)123-45-67",
                Role.USER
        );
    }
}

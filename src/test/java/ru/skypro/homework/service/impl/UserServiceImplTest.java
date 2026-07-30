package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit-тесты сервиса работы с профилем пользователя.
 */
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private static final String USERNAME = "user@mail.ru";
    private static final Long USER_ID = 1L;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(
                userRepository,
                userMapper,
                passwordEncoder
        );
    }

    @Test
    @DisplayName("Получение профиля возвращает DTO пользователя")
    void getUserShouldReturnUserDto() {
        UserEntity userEntity = createUserEntity();

        User expectedUser = new User();
        expectedUser.setId(USER_ID.intValue());
        expectedUser.setEmail(USERNAME);
        expectedUser.setFirstName("Юрий");
        expectedUser.setLastName("Ермилов");
        expectedUser.setPhone("+7(999)123-45-67");

        when(userRepository.findByUsernameIgnoreCase(USERNAME))
                .thenReturn(Optional.of(userEntity));

        when(userMapper.toDto(userEntity))
                .thenReturn(expectedUser);

        User actualUser = userService.getUser(USERNAME);

        assertEquals(expectedUser, actualUser);

        verify(userRepository)
                .findByUsernameIgnoreCase(USERNAME);

        verify(userMapper)
                .toDto(userEntity);
    }

    @Test
    @DisplayName("Получение профиля неизвестного пользователя вызывает исключение")
    void getUserShouldThrowWhenUserDoesNotExist() {
        when(userRepository.findByUsernameIgnoreCase(USERNAME))
                .thenReturn(Optional.empty());

        UsernameNotFoundException exception =
                assertThrows(
                        UsernameNotFoundException.class,
                        () -> userService.getUser(USERNAME)
                );

        assertEquals(
                "Пользователь user@mail.ru не найден",
                exception.getMessage()
        );

        verify(userMapper, never())
                .toDto(org.mockito.ArgumentMatchers.any());
    }

    @Test
    @DisplayName("Обновление профиля сохраняет изменённого пользователя")
    void updateUserShouldSaveChangesAndReturnDto() {
        UserEntity userEntity = createUserEntity();

        UpdateUser request = new UpdateUser(
                "Иван",
                "Петров",
                "+7(999)555-44-33"
        );

        UpdateUser expectedResponse = new UpdateUser(
                "Иван",
                "Петров",
                "+7(999)555-44-33"
        );

        when(userRepository.findByUsernameIgnoreCase(USERNAME))
                .thenReturn(Optional.of(userEntity));

        when(userRepository.save(userEntity))
                .thenReturn(userEntity);

        when(userMapper.toUpdateUser(userEntity))
                .thenReturn(expectedResponse);

        UpdateUser actualResponse =
                userService.updateUser(
                        USERNAME,
                        request
                );

        assertEquals(expectedResponse, actualResponse);

        verify(userMapper)
                .updateEntity(userEntity, request);

        verify(userRepository)
                .save(userEntity);

        verify(userMapper)
                .toUpdateUser(userEntity);
    }

    @Test
    @DisplayName("Смена пароля успешна при правильном текущем пароле")
    void setPasswordShouldEncodeAndSaveNewPassword() {
        UserEntity userEntity = createUserEntity();
        userEntity.setPassword("encoded-old-password");

        NewPassword request = new NewPassword(
                "old-password",
                "new-password"
        );

        when(userRepository.findByUsernameIgnoreCase(USERNAME))
                .thenReturn(Optional.of(userEntity));

        when(passwordEncoder.matches(
                "old-password",
                "encoded-old-password"
        )).thenReturn(true);

        when(passwordEncoder.encode("new-password"))
                .thenReturn("encoded-new-password");

        boolean result =
                userService.setPassword(
                        USERNAME,
                        request
                );

        assertTrue(result);

        assertEquals(
                "encoded-new-password",
                userEntity.getPassword()
        );

        verify(passwordEncoder)
                .matches(
                        "old-password",
                        "encoded-old-password"
                );

        verify(passwordEncoder)
                .encode("new-password");

        verify(userRepository)
                .save(userEntity);
    }

    @Test
    @DisplayName("Смена пароля отклоняется при неверном текущем пароле")
    void setPasswordShouldReturnFalseWhenCurrentPasswordIsWrong() {
        UserEntity userEntity = createUserEntity();
        userEntity.setPassword("encoded-old-password");

        NewPassword request = new NewPassword(
                "wrong-password",
                "new-password"
        );

        when(userRepository.findByUsernameIgnoreCase(USERNAME))
                .thenReturn(Optional.of(userEntity));

        when(passwordEncoder.matches(
                "wrong-password",
                "encoded-old-password"
        )).thenReturn(false);

        boolean result =
                userService.setPassword(
                        USERNAME,
                        request
                );

        assertFalse(result);

        verify(passwordEncoder, never())
                .encode(org.mockito.ArgumentMatchers.anyString());

        verify(userRepository, never())
                .save(org.mockito.ArgumentMatchers.any());
    }

    @Test
    @DisplayName("Загрузка аватара сохраняет байты и MIME-тип")
    void updateImageShouldSaveImageAndMediaType() {
        UserEntity userEntity = createUserEntity();

        byte[] imageBytes = {
                10,
                20,
                30
        };

        when(userRepository.findByUsernameIgnoreCase(USERNAME))
                .thenReturn(Optional.of(userEntity));

        userService.updateImage(
                USERNAME,
                imageBytes,
                "image/png"
        );

        assertArrayEquals(
                imageBytes,
                userEntity.getImage()
        );

        assertEquals(
                "image/png",
                userEntity.getImageMediaType()
        );

        verify(userRepository)
                .save(userEntity);
    }

    @Test
    @DisplayName("Получение аватара возвращает сохранённые байты")
    void getImageShouldReturnImageBytes() {
        UserEntity userEntity = createUserEntity();

        byte[] expectedImage = {
                1,
                2,
                3
        };

        userEntity.setImage(expectedImage);

        when(userRepository.findById(USER_ID))
                .thenReturn(Optional.of(userEntity));

        byte[] actualImage =
                userService.getImage(USER_ID);

        assertArrayEquals(
                expectedImage,
                actualImage
        );
    }

    @Test
    @DisplayName("Получение отсутствующего аватара вызывает исключение")
    void getImageShouldThrowWhenImageIsMissing() {
        UserEntity userEntity = createUserEntity();
        userEntity.setImage(null);

        when(userRepository.findById(USER_ID))
                .thenReturn(Optional.of(userEntity));

        EntityNotFoundException exception =
                assertThrows(
                        EntityNotFoundException.class,
                        () -> userService.getImage(USER_ID)
                );

        assertEquals(
                "Аватар пользователя с id 1 не найден",
                exception.getMessage()
        );
    }

    @Test
    @DisplayName("Получение MIME-типа возвращает сохранённое значение")
    void getImageMediaTypeShouldReturnStoredValue() {
        UserEntity userEntity = createUserEntity();
        userEntity.setImageMediaType("image/jpeg");

        when(userRepository.findById(USER_ID))
                .thenReturn(Optional.of(userEntity));

        String result =
                userService.getImageMediaType(USER_ID);

        assertEquals(
                "image/jpeg",
                result
        );
    }

    @Test
    @DisplayName("При отсутствии MIME-типа возвращается application/octet-stream")
    void getImageMediaTypeShouldReturnDefaultValue() {
        UserEntity userEntity = createUserEntity();
        userEntity.setImageMediaType(null);

        when(userRepository.findById(USER_ID))
                .thenReturn(Optional.of(userEntity));

        String result =
                userService.getImageMediaType(USER_ID);

        assertEquals(
                "application/octet-stream",
                result
        );
    }

    @Test
    @DisplayName("Поиск пользователя по неизвестному id вызывает исключение")
    void getImageMediaTypeShouldThrowWhenUserDoesNotExist() {
        when(userRepository.findById(USER_ID))
                .thenReturn(Optional.empty());

        EntityNotFoundException exception =
                assertThrows(
                        EntityNotFoundException.class,
                        () -> userService.getImageMediaType(USER_ID)
                );

        assertEquals(
                "Пользователь с id 1 не найден",
                exception.getMessage()
        );
    }

    private UserEntity createUserEntity() {
        UserEntity user = new UserEntity();

        user.setId(USER_ID);
        user.setUsername(USERNAME);
        user.setPassword("encoded-password");
        user.setFirstName("Юрий");
        user.setLastName("Ермилов");
        user.setPhone("+7(999)123-45-67");

        return user;
    }
}

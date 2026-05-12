package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.Role;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.EntityNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final UserMapper userMapper = mock(UserMapper.class);
    private final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
    private final ImageService imageService = mock(ImageService.class);
    private final Authentication authentication = mock(Authentication.class);

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository, userMapper, passwordEncoder, imageService);
    }

    @Test
    void getUser_whenUserExists_shouldReturnUserDto() {
        // Given
        String email = "user@sky.pro";
        UserEntity user = new UserEntity();
        user.setId(1);
        user.setEmail(email);
        user.setFirstName("Иван");
        user.setLastName("Иванов");
        user.setPhone("+79991234567");
        user.setRole(Role.USER);

        User expectedDto = new User();
        expectedDto.setId(1);
        expectedDto.setEmail(email);
        expectedDto.setFirstName("Иван");
        expectedDto.setLastName("Иванов");
        expectedDto.setPhone("+79991234567");
        expectedDto.setRole(Role.USER);
        expectedDto.setImage("/images/user/1.jpg");

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(expectedDto);

        // When
        User result = userService.getUser(email);

        // Then
        assertThat(result).isEqualTo(expectedDto);
        verify(userRepository).findByEmail(email);
        verify(userMapper).toDto(user);
    }

    @Test
    void getUser_whenUserNotFound_shouldThrowUsernameNotFoundException() {
        // Given
        String email = "unknown@sky.pro";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> userService.getUser(email)).isInstanceOf(org.springframework.security.core.userdetails.UsernameNotFoundException.class).hasMessage("User not found");
        verify(userRepository).findByEmail(email);
    }

    @Test
    void updateUser_whenValidData_shouldUpdateAndReturnUser() {
        // Given
        String email = "user@sky.pro";
        UpdateUser updateUser = new UpdateUser();
        updateUser.setFirstName("Петр");
        updateUser.setLastName("Петров");
        updateUser.setPhone("+79876543210");

        UserEntity user = new UserEntity();
        user.setId(1);
        user.setEmail(email);
        user.setFirstName("Иван");
        user.setLastName("Иванов");
        user.setPhone("+79991234567");

        UserEntity updatedUser = new UserEntity();
        updatedUser.setId(1);
        updatedUser.setEmail(email);
        updatedUser.setFirstName("Петр");
        updatedUser.setLastName("Петров");
        updatedUser.setPhone("+79876543210");

        User expectedDto = new User();
        expectedDto.setId(1);
        expectedDto.setEmail(email);
        expectedDto.setFirstName("Петр");
        expectedDto.setLastName("Петров");
        expectedDto.setPhone("+79876543210");

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(updatedUser);
        when(userMapper.toDto(updatedUser)).thenReturn(expectedDto);

        // When
        User result = userService.updateUser(email, updateUser);

        // Then
        assertThat(result).isEqualTo(expectedDto);
        assertThat(user.getFirstName()).isEqualTo("Петр");
        assertThat(user.getLastName()).isEqualTo("Петров");
        assertThat(user.getPhone()).isEqualTo("+79876543210");
        verify(userRepository).findByEmail(email);
        verify(userRepository).save(user);
        verify(userMapper).toDto(updatedUser);
    }

    @Test
    void updatePassword_whenCurrentPasswordMatches_shouldChangePassword() {
        // Given
        String email = "user@sky.pro";
        NewPassword newPassword = new NewPassword();
        newPassword.setCurrentPassword("oldPass");
        newPassword.setNewPassword("newPass");

        UserEntity user = new UserEntity();
        user.setId(1);
        user.setEmail(email);
        user.setPassword("$2a$10$hashedOldPass");

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("oldPass", "$2a$10$hashedOldPass")).thenReturn(true);
        when(passwordEncoder.encode("newPass")).thenReturn("$2a$10$hashedNewPass");

        // When
        userService.updatePassword(email, newPassword);

        // Then
        assertThat(user.getPassword()).isEqualTo("$2a$10$hashedNewPass");
        verify(userRepository).findByEmail(email);
        verify(passwordEncoder).matches("oldPass", "$2a$10$hashedOldPass");
        verify(passwordEncoder).encode("newPass");
        verify(userRepository).save(user);
    }

    @Test
    void updatePassword_whenCurrentPasswordDoesNotMatch_shouldThrowBadCredentialsException() {
        // Given
        String email = "user@sky.pro";
        NewPassword newPassword = new NewPassword();
        newPassword.setCurrentPassword("wrong");
        newPassword.setNewPassword("newPass");

        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setPassword("$2a$10$hashedOldPass");

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrong", "$2a$10$hashedOldPass")).thenReturn(false);

        // When & Then
        assertThatThrownBy(() -> userService.updatePassword(email, newPassword)).isInstanceOf(BadCredentialsException.class).hasMessage("Invalid current password");
        verify(userRepository).findByEmail(email);
        verify(passwordEncoder).matches("wrong", "$2a$10$hashedOldPass");
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).save(any());
    }

    @Test
    void updateUserImage_whenUserExists_shouldSaveImage() throws IOException {
        // Given
        String email = "user@sky.pro";
        byte[] imageBytes = "FAKE_IMAGE_DATA".getBytes();
        UserEntity user = new UserEntity();
        user.setId(1);
        user.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        doNothing().when(imageService).saveUserImage(1, imageBytes);

        // When
        userService.updateUserImage(email, imageBytes);

        // Then
        verify(userRepository).findByEmail(email);
        verify(imageService).saveUserImage(1, imageBytes);
    }

    @Test
    void updateUserImage_whenUserNotFound_shouldThrowEntityNotFoundException() throws IOException {
        // Given
        String email = "unknown@sky.pro";
        byte[] imageBytes = "FAKE_IMAGE_DATA".getBytes();

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> userService.updateUserImage(email, imageBytes)).isInstanceOf(EntityNotFoundException.class).hasMessage("Пользователь не найден");
        verify(userRepository).findByEmail(email);
        verify(imageService, never()).saveUserImage(anyInt(), any());
    }

    @Test
    void updateUserImage_whenImageSaveFails_shouldThrowRuntimeException() throws IOException {
        // Given
        String email = "user@sky.pro";
        byte[] imageBytes = "FAKE_IMAGE_DATA".getBytes();
        UserEntity user = new UserEntity();
        user.setId(1);
        user.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        doThrow(new IOException("Save failed")).when(imageService).saveUserImage(1, imageBytes);

        // When & Then
        assertThatThrownBy(() -> userService.updateUserImage(email, imageBytes)).isInstanceOf(RuntimeException.class).hasMessage("Ошибка сохранения изображения").hasCauseInstanceOf(IOException.class);
        verify(userRepository).findByEmail(email);
        verify(imageService).saveUserImage(1, imageBytes);
    }

    @Test
    void changePassword_withAuthentication_whenCurrentPasswordMatches_shouldChangePassword() {
        // Given
        String currentPassword = "oldPass";
        String newPassword = "newPass";

        UserEntity user = new UserEntity();
        user.setId(1);
        user.setEmail("user@sky.pro");
        user.setPassword("$2a$10$hashedOldPass");

        when(authentication.getPrincipal()).thenReturn(user);
        when(passwordEncoder.matches(currentPassword, "$2a$10$hashedOldPass")).thenReturn(true);
        when(passwordEncoder.encode(newPassword)).thenReturn("$2a$10$hashedNewPass");

        // When
        userService.changePassword(currentPassword, newPassword, authentication);

        // Then
        assertThat(user.getPassword()).isEqualTo("$2a$10$hashedNewPass");
        verify(passwordEncoder).matches(currentPassword, "$2a$10$hashedOldPass");
        verify(passwordEncoder).encode(newPassword);
        verify(userRepository).save(user);
    }

    @Test
    void changePassword_withAuthentication_whenCurrentPasswordDoesNotMatch_shouldThrowBadCredentialsException() {
        // Given
        String currentPassword = "wrong";
        String newPassword = "newPass";

        UserEntity user = new UserEntity();
        user.setPassword("$2a$10$hashedOldPass");

        when(authentication.getPrincipal()).thenReturn(user);
        when(passwordEncoder.matches("wrong", "$2a$10$hashedOldPass")).thenReturn(false);

        // When & Then
        assertThatThrownBy(() -> userService.changePassword(currentPassword, newPassword, authentication)).isInstanceOf(BadCredentialsException.class).hasMessage("Current password is incorrect");
        verify(passwordEncoder).matches("wrong", "$2a$10$hashedOldPass");
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).save(any());
    }
}
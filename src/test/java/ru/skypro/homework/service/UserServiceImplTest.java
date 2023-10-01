package ru.skypro.homework.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.UserServiceImpl;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder encoder;
    @Mock
    private ImageService imageService;
    @InjectMocks
    private UserServiceImpl out;

    User user = new User(1, "Bob", "Mjn", "name", "password", "2332343212", "USER", true,"gdhfhfhdh");

    @Test
    void updatePassword() {
        when(userRepository.findUserByUsername(user.getUsername()))
                .thenReturn(Optional.ofNullable(user));
        User newuser = new User(1, "Bob", "Mjn", "name", "newpassword", "2332343212", "USER", true,"gdhfhfhdh");
        when(userRepository.save(user))
                .thenReturn(newuser);
        String encryptedPassword = "password";
        NewPasswordDto newPasswordDto = new NewPasswordDto("password", "newpassword");
        when(encoder.matches(newPasswordDto.getCurrentPassword(), encryptedPassword)).thenReturn(true);
        out.updatePassword(user.getUsername(), newPasswordDto);
        assertEquals(newuser.getPassword(),
                newPasswordDto.getNewPassword());
        verify(userRepository, times(1)).findUserByUsername(user.getUsername());
    }

    @Test
    void getUserInformation() {
        when(userRepository.findUserByUsername(user.getUsername()))
                .thenReturn(Optional.ofNullable(user));
        assertEquals(out.getUserInformation(user.getUsername()), UserDto.fromUser(user));
        verify(userRepository, times(1)).findUserByUsername(user.getUsername());
    }

    @Test
    void updateUser() {
        when(userRepository.findUserByUsername(user.getUsername()))
                .thenReturn(Optional.ofNullable(user));
        UpdateUserDto updateUserDto = new UpdateUserDto("Cip", "PolL", "123445");
        assertEquals(out.updateUser(user.getUsername(), updateUserDto).getFirstName(), updateUserDto.getFirstName());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void updateUserAvatar() {
        MultipartFile file = null;
        when(userRepository.findUserByUsername(user.getUsername()))
                .thenReturn(Optional.ofNullable(user));
        when(imageService.uploadImage(file)).thenReturn("NewImage");
        out.updateUserAvatar(user.getUsername(), file);
        assertEquals(user.getImage(), "NewImage");
        verify(imageService, times(1)).uploadImage(file);
    }
}

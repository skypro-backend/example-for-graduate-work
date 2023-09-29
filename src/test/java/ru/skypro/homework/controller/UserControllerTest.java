package ru.skypro.homework.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    private UserService userService;
    @Mock
    private ImageService imageService;

    @Mock
    private Authentication authentication;


    @InjectMocks
    private UserController out;
    User user = new User(1, "Bob",
            "Mjn", "name",
            "password", "2332343212",
            "USER", true, "gdhfhfhdh");
    NewPasswordDto newPasswordDto = new NewPasswordDto("password", "newPassword");
    UpdateUserDto updateUserDto = new UpdateUserDto("Mot", "Lack", "2323209899898");
    MultipartFile file = new MockMultipartFile("qwer",
            "foto", "jpeg", new byte[1]);

    @Test
    void setPassword() {

        when(authentication.getName()).thenReturn("name");
        out.setPassword(newPasswordDto, authentication);
        verify(userService, times(1)).updatePassword(user.getUsername(), newPasswordDto);
    }

    @Test
    void getUser() {
        when(userService.getUserInformation(authentication.getName())).thenReturn(UserDto.fromUser(user));
        UserDto userDto = UserDto.fromUser(user);
        assertEquals(out.getUser(authentication).getBody(), userDto);
        verify(userService, times(1)).getUserInformation(authentication.getName());
    }

    @Test
    void updateUser() {
        User updateduser = user;
        updateduser.setFirstName(updateUserDto.getFirstName());
        updateduser.setLastName(updateUserDto.getLastName());
        updateduser.setPhone(updateUserDto.getPhone());
        when(userService.updateUser(user.getUsername(), updateUserDto))
                .thenReturn(UserDto.fromUser(updateduser));
        when(authentication.getName()).thenReturn(user.getUsername());
        assertEquals(out.updateUser(updateUserDto, authentication).getBody(), UserDto.fromUser(updateduser));
        verify(userService, times(1)).updateUser(user.getUsername(), updateUserDto);
    }

    @Test
    void image() {

        when(authentication.getName()).thenReturn(user.getUsername());
        out.image(file, authentication);

        verify(userService, times(1)).updateUserAvatar(user.getUsername(),file);
    }

    @Test
    void getImage() throws IOException {
        when(imageService.getImage("gdhfhfhdh")).thenReturn(file.getBytes());
        assertEquals(out.getImage(user.getImage()), file.getBytes());
        verify(imageService, times(1)).getImage(user.getImage());
    }
}

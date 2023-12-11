package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.exception.IncorrectPasswordException;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.Role;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private ImageService imageService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Method under test: {@link UserServiceImpl#getCurrentUser()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCurrentUser() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.security.core.Authentication.getName()" because "auth" is null
        //       at ru.skypro.homework.service.impl.UserServiceImpl.getCurrentUser(UserServiceImpl.java:43)
        //   See https://diff.blue/R013 to resolve this issue.

        userServiceImpl.getCurrentUser();
    }

    /**
     * Method under test: {@link UserServiceImpl#updateUser(UpdateUserDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateUser() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.security.core.Authentication.getName()" because "auth" is null
        //       at ru.skypro.homework.service.impl.UserServiceImpl.updateUser(UserServiceImpl.java:54)
        //   See https://diff.blue/R013 to resolve this issue.

        userServiceImpl.updateUser(new UpdateUserDTO());
    }

    /**
     * Method under test: {@link UserServiceImpl#setPassword(NewPasswordDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSetPassword() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.security.core.Authentication.getName()" because "auth" is null
        //       at ru.skypro.homework.service.impl.UserServiceImpl.setPassword(UserServiceImpl.java:66)
        //   See https://diff.blue/R013 to resolve this issue.

        userServiceImpl.setPassword(new NewPasswordDTO("iloveyou", "iloveyou"));
    }

    @Test
    void testUpdateUserImage() throws IOException {
        Image image = new Image();
        image.setData("AXAXAXAX".getBytes("UTF-8"));
        image.setFileSize(3L);
        image.setId(1L);
        image.setMediaType("Media Type");

        User user = new User();
        user.setAds(new ArrayList<>());
        user.setComments(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1L);
        user.setImage(image);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhone("6625550144");
        user.setRole(Role.USER);

        Image image2 = new Image();
        image2.setData("AXAXAXAX".getBytes("UTF-8"));
        image2.setFileSize(3L);
        image2.setId(1L);
        image2.setMediaType("Media Type");

        User user2 = new User();
        user2.setAds(new ArrayList<>());
        user2.setComments(new ArrayList<>());
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(1L);
        user2.setImage(image2);
        user2.setLastName("Doe");
        user2.setPassword("iloveyou");
        user2.setPhone("6625550144");
        user2.setRole(Role.USER);
        when(userRepository.save(Mockito.<User>any())).thenReturn(user2);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(user);

        Image image3 = new Image();
        image3.setData("AXAXAXAX".getBytes("UTF-8"));
        image3.setFileSize(3L);
        image3.setId(1L);
        image3.setMediaType("Media Type");
        when(imageService.addImage(Mockito.<MultipartFile>any())).thenReturn(image3);
        doNothing().when(imageService).deleteImage(Mockito.<Long>any());
        userServiceImpl.updateUserImage(
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))), "janedoe");
        verify(userRepository).save(Mockito.<User>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
        verify(imageService).addImage(Mockito.<MultipartFile>any());
        verify(imageService).deleteImage(Mockito.<Long>any());
    }

    /**
     * Method under test:
     * {@link UserServiceImpl#updateUserImage(MultipartFile, String)}
     */
    @Test
    void testUpdateUserImage2() throws IOException {
        Image image = new Image();
        image.setData("AXAXAXAX".getBytes("UTF-8"));
        image.setFileSize(3L);
        image.setId(1L);
        image.setMediaType("Media Type");

        User user = new User();
        user.setAds(new ArrayList<>());
        user.setComments(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1L);
        user.setImage(image);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhone("6625550144");
        user.setRole(Role.USER);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(user);
        when(imageService.addImage(Mockito.<MultipartFile>any()))
                .thenThrow(new IncorrectPasswordException("An error occurred"));
        assertThrows(IncorrectPasswordException.class, () -> userServiceImpl.updateUserImage(
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))), "janedoe"));
        verify(userRepository).findByEmail(Mockito.<String>any());
        verify(imageService).addImage(Mockito.<MultipartFile>any());
    }
}

package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.image.ImageStorageService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class UserServiceImplTest {


    private UserRepository userRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;
    private ImageStorageService imageStorageService;

    private UserServiceImpl userService;



    @BeforeEach
    void setUp() {

        userRepository = mock(UserRepository.class);
        userMapper = mock(UserMapper.class);
        passwordEncoder = mock(PasswordEncoder.class);
        imageStorageService = mock(ImageStorageService.class);


        userService = new UserServiceImpl(
                userRepository,
                userMapper,
                passwordEncoder,
                imageStorageService
        );


        SecurityContextHolder
                .getContext()
                .setAuthentication(
                        new UsernamePasswordAuthenticationToken(
                                "test@mail.ru",
                                null
                        )
                );
    }



    @AfterEach
    void clearSecurityContext() {

        SecurityContextHolder.clearContext();
    }





    @Test
    void shouldGetCurrentUser() {


        User user = new User();

        user.setEmail("test@mail.ru");



        when(userRepository.findByEmail(
                "test@mail.ru"
        ))
                .thenReturn(
                        Optional.of(user)
                );


        ru.skypro.homework.dto.User dto =
                new ru.skypro.homework.dto.User();


        when(userMapper.toDto(user))
                .thenReturn(dto);



        ru.skypro.homework.dto.User result =
                userService.getUser();



        assertThat(result)
                .isEqualTo(dto);



        verify(userRepository)
                .findByEmail(
                        "test@mail.ru"
                );
    }





    @Test
    void shouldUpdateUser() {


        User user = new User();



        when(userRepository.findByEmail(
                "test@mail.ru"
        ))
                .thenReturn(
                        Optional.of(user)
                );



        when(userRepository.save(user))
                .thenReturn(user);



        UpdateUser updateUser =
                new UpdateUser();



        ru.skypro.homework.dto.User dto =
                new ru.skypro.homework.dto.User();



        when(userMapper.toDto(user))
                .thenReturn(dto);



        ru.skypro.homework.dto.User result =
                userService.updateUser(
                        updateUser
                );



        assertThat(result)
                .isEqualTo(dto);



        verify(userMapper)
                .updateUserFromDto(
                        updateUser,
                        user
                );


        verify(userRepository)
                .save(user);
    }






    @Test
    void shouldUpdatePasswordSuccessfully() {


        User user = new User();


        user.setPassword(
                "oldEncodedPassword"
        );



        when(userRepository.findByEmail(
                "test@mail.ru"
        ))
                .thenReturn(
                        Optional.of(user)
                );



        when(passwordEncoder.matches(
                "oldPassword",
                "oldEncodedPassword"
        ))
                .thenReturn(true);



        when(passwordEncoder.encode(
                "newPassword"
        ))
                .thenReturn(
                        "newEncodedPassword"
                );



        NewPassword newPassword =
                new NewPassword();


        newPassword.setCurrentPassword(
                "oldPassword"
        );


        newPassword.setNewPassword(
                "newPassword"
        );



        userService.updatePassword(
                newPassword
        );



        assertThat(user.getPassword())
                .isEqualTo(
                        "newEncodedPassword"
                );


        verify(userRepository)
                .save(user);
    }







    @Test
    void shouldThrowExceptionWhenCurrentPasswordWrong() {


        User user = new User();


        user.setPassword(
                "oldEncodedPassword"
        );



        when(userRepository.findByEmail(
                "test@mail.ru"
        ))
                .thenReturn(
                        Optional.of(user)
                );



        when(passwordEncoder.matches(
                "wrongPassword",
                "oldEncodedPassword"
        ))
                .thenReturn(false);



        NewPassword newPassword =
                new NewPassword();


        newPassword.setCurrentPassword(
                "wrongPassword"
        );


        newPassword.setNewPassword(
                "newPassword"
        );



        assertThatThrownBy(
                () ->
                        userService.updatePassword(
                                newPassword
                        )
        )
                .isInstanceOf(
                        IllegalArgumentException.class
                );



        verify(userRepository, never())
                .save(any());
    }

}
package ru.skypro.homework.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework.service.AuthService;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {
    @Mock
    AuthService authService;
    @InjectMocks
    AuthController authController;

    @Test
    @DisplayName("login_test_from_AuthController")
    void login_() {
        //given
        String userName;
        String password;

        //when
        //then

    }

    @Test
    @DisplayName("register_test_from_AuthController")
    void register() {
    }
}
package ru.skypro.homework.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.skypro.homework.dto.Login;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.service.AuthService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Test
    void login_ValidCredentials_ReturnsOk() throws Exception {
        when(authService.login("user@test.com", "password123")).thenReturn(true);

        mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content("{" + "\"username\":\"user@test.com\"," + "\"password\":\"password123\"" + "}")).andExpect(status().isOk());
    }

    @Test
    void login_InvalidCredentials_ReturnsUnauthorized() throws Exception {
        when(authService.login(anyString(), anyString())).thenReturn(false);

        mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content("{" + "\"username\":\"user@test.com\"," + "\"password\":\"wrong\"" + "}")).andExpect(status().isUnauthorized());
    }

    @Test
    void register_ValidData_ReturnsCreated() throws Exception {
        when(authService.register(any(Register.class))).thenReturn(true);

        mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON).content("{" + "\"username\":\"user@test.com\"," + "\"password\":\"password123\"," + "\"firstName\":\"Ivan\"," + "\"lastName\":\"Ivanov\"," + "\"phone\":\"+79991234567\"," + "\"role\":\"USER\"" + "}")).andExpect(status().isCreated());
    }

    @Test
    void register_UserExists_ReturnsBadRequest() throws Exception {
        when(authService.register(any(Register.class))).thenReturn(false);

        mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON).content("{" + "\"username\":\"user@test.com\"," + "\"password\":\"password123\"," + "\"firstName\":\"Ivan\"," + "\"lastName\":\"Ivanov\"," + "\"phone\":\"+79991234567\"" + "}")).andExpect(status().isBadRequest());
    }
}
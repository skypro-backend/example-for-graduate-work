package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.skypro.homework.dto.Login;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.security.TestSecurityConfig;
import ru.skypro.homework.service.AuthService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@Import(TestSecurityConfig.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@DisplayName("Тесты контроллера аутентификации")
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthService authService;

    @Test
    @DisplayName("POST /login - успешная авторизация валидным пользователем")
    void login_ValidUser_ReturnsOk() throws Exception {
        Login login = new Login();
        login.setUsername("user@example.com");
        login.setPassword("password123");

        when(authService.login(anyString(), anyString())).thenReturn(true);

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /login - ошибка авторизации невалидным пользователем")
    void login_InvalidUser_ReturnsUnauthorized() throws Exception {
        Login login = new Login();
        login.setUsername("invalid@example.com");
        login.setPassword("wrongpassword");

        when(authService.login(anyString(), anyString())).thenReturn(false);

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("POST /register - успешная регистрация нового пользователя")
    void register_ValidUser_ReturnsCreated() throws Exception {
        Register register = new Register();
        register.setUsername("newuser@example.com");
        register.setPassword("password123");
        register.setFirstName("Иван");
        register.setLastName("Иванов");
        register.setPhone("+79991234567");
        register.setRole(Role.USER);

        when(authService.register(any(Register.class))).thenReturn(true);

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(register)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("POST /register - ошибка при регистрации с существующим email")
    void register_ExistingUser_ReturnsBadRequest() throws Exception {
        Register register = new Register();
        register.setUsername("existing@example.com");
        register.setPassword("password123");
        register.setFirstName("Иван");
        register.setLastName("Иванов");
        register.setPhone("+79991234567");
        register.setRole(Role.USER);

        when(authService.register(any(Register.class))).thenReturn(false);

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(register)))
                .andExpect(status().isBadRequest());
    }
}
package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.Login;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.Role;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class AuthControllerIntegrationTest {

    private static final String RAW_PASSWORD = "password123";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void register_withNewEmail_shouldReturn201AndCreateUserWithHashedPassword() throws Exception {
        Register register = new Register();
        register.setUsername("newuser@mail.com");
        register.setPassword(RAW_PASSWORD);
        register.setFirstName("Ivan");
        register.setLastName("Ivanov");
        register.setPhone("+79991234567");
        register.setRole(ru.skypro.homework.dto.Role.USER);

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(register)))
                .andExpect(status().isCreated());

        User savedUser = userRepository.findByEmail("newuser@mail.com").orElseThrow();
        assertThat(savedUser.getPassword()).isNotEqualTo(RAW_PASSWORD);
        assertThat(passwordEncoder.matches(RAW_PASSWORD, savedUser.getPassword())).isTrue();
    }

    @Test
    void register_withExistingEmail_shouldReturn400() throws Exception {
        createUser("duplicate@mail.com");

        Register register = new Register();
        register.setUsername("duplicate@mail.com");
        register.setPassword(RAW_PASSWORD);
        register.setFirstName("Petr");
        register.setLastName("Petrov");
        register.setPhone("+79997654321");
        register.setRole(ru.skypro.homework.dto.Role.USER);

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(register)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void login_withCorrectCredentials_shouldReturn200() throws Exception {
        createUser("login-test@mail.com");

        Login login = new Login();
        login.setUsername("login-test@mail.com");
        login.setPassword(RAW_PASSWORD);

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(login)))
                .andExpect(status().isOk());
    }

    @Test
    void login_withWrongPassword_shouldReturn401() throws Exception {
        createUser("wrong-pass@mail.com");

        Login login = new Login();
        login.setUsername("wrong-pass@mail.com");
        login.setPassword("WrongPassword");

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(login)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void login_withNonExistingUser_shouldReturn401() throws Exception {
        Login login = new Login();
        login.setUsername("ghost@mail.com");
        login.setPassword(RAW_PASSWORD);

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(login)))
                .andExpect(status().isUnauthorized());
    }

    private User createUser(String email) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(RAW_PASSWORD));
        user.setFirstName("Test");
        user.setLastName("User");
        user.setRole(Role.USER);
        return userRepository.save(user);
    }
}

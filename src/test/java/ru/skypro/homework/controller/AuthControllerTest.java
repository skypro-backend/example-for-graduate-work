package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.skypro.homework.dto.account.Login;
import ru.skypro.homework.dto.account.Register;
import ru.skypro.homework.dto.account.Role;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.TestService;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private TestService testService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @AfterEach
    public void clearDB() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName(value = "Авторизация пользователя")
    @WithMockUser(value = "testEmail@gmail.com")
    void shouldReturnOkWhenAuthPassed() throws Exception {
        testService.createTestUser();
        Login login = new Login();
        login.setPassword("password");
        login.setUsername("testEmail@gmail.com");
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .content(objectMapper.writeValueAsString(login))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Регистрация пользователя")
    @WithMockUser(value = "testEmail@gmail.com")
    void shouldReturnCreatedWhenRegistryPassed() throws Exception {
        Register register = new Register();
        register.setUsername("testUserEmail@gmail.com");
        register.setPassword("testUserPassword");
        register.setFirstName("testUserFirstName");
        register.setLastName("testUserLastName");
        register.setPhone("+79444444444");
        register.setRole(Role.USER);
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .content(objectMapper.writeValueAsString(register))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
        Assertions.assertTrue(userRepository.findByEmail(register.getUsername()).isPresent());
        UserEntity newUser = userRepository.findByEmail(register.getUsername()).get();
        Assertions.assertEquals(newUser.getEmail(), register.getUsername());
        Assertions.assertTrue(passwordEncoder.matches(register.getPassword(), newUser.getPassword()));
        Assertions.assertEquals(newUser.getFirstName(), register.getFirstName());
        Assertions.assertEquals(newUser.getLastName(), register.getLastName());
        Assertions.assertEquals(newUser.getPhoneUser(), register.getPhone());
        Assertions.assertEquals(newUser.getRole(), register.getRole());
    }
}

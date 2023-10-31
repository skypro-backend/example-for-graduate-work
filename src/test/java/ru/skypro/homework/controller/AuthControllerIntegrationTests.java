package ru.skypro.homework.controller;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.skypro.homework.dto.Login;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.Users;
import ru.skypro.homework.repository.UsersRepository;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class AuthControllerIntegrationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private AuthService authService;

    @Autowired
    private UsersRepository usersRepository;

    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest")
            .withUsername("postgres")
            .withPassword("73aberiv");

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }


    private void addToDb() {
        usersRepository.deleteAll();
        Users user = new Users(1,
                null,
                "user@gmail.com",
                "$2a$10$mShIMZIKnJ.EVqUycC2OE.qunAUqKJPFZq6ADSuJ.IYmVWBmXqWMi",
                "ivan",
                "ivanov",
                "+7(777)777-77-77",
                Role.USER);
        usersRepository.save(user);
    }

    @Test
    public void login_status_isOk() throws Exception {
        addToDb();
        JSONObject login = new JSONObject();
        login.put("username", "user@gmail.com");
        login.put("password", "password");
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(login.toString()))
                .andExpect(status().isOk());
    }

    @Test
    public void login_status_throw401() throws Exception {
        addToDb();
        JSONObject login = new JSONObject();
        login.put("username", "admin@gmail.com");
        login.put("password", "password1");
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(login.toString()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void register_status_is201() throws Exception {
        usersRepository.deleteAll();
        JSONObject register = new JSONObject();
        register.put("username", "user@gmail.com");
        register.put("password", "password");
        register.put("firstName", "user");
        register.put("lastName", "userovich");
        register.put("phone", "+7(777)777-77-77");
        register.put("role", Role.USER);
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(register.toString()))
                .andExpect(status().isCreated());
    }

    @Test
    public void register_status_throw400() throws Exception {
        addToDb();
        JSONObject register = new JSONObject();
        register.put("username", "user@gmail.com");
        register.put("password", "password");
        register.put("firstName", "user");
        register.put("lastName", "userovich");
        register.put("phone", "+7(777)777-77-77");
        register.put("role", Role.USER);
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(register.toString()))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void register_status_isNotValid_firstname() throws Exception {
        usersRepository.deleteAll();
        JSONObject register = new JSONObject();
        register.put("username", "user@gmail.com");
        register.put("password", "password");
        register.put("firstName", "userrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
        register.put("lastName", "userovich");
        register.put("phone", "+7(777)777-77-77");
        register.put("role", Role.USER);
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(register.toString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void register_status_isNotValid_username() throws Exception {
        usersRepository.deleteAll();
        JSONObject register = new JSONObject();
        register.put("username", "user@gmail.commmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
        register.put("password", "password");
        register.put("firstName", "user");
        register.put("lastName", "userovich");
        register.put("phone", "+7(777)777-77-77");
        register.put("role", Role.USER);
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(register.toString()))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void register_status_isNotValid_password() throws Exception {
        usersRepository.deleteAll();
        JSONObject register = new JSONObject();
        register.put("username", "user@gmail.com");
        register.put("password", "passwordddddddddddddd");
        register.put("firstName", "user");
        register.put("lastName", "userovich");
        register.put("phone", "+7(777)777-77-77");
        register.put("role", Role.USER);
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(register.toString()))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void register_status_isNotValid_lastname() throws Exception {
        usersRepository.deleteAll();
        JSONObject register = new JSONObject();
        register.put("username", "user@gmail.com");
        register.put("password", "password");
        register.put("firstName", "user");
        register.put("lastName", "userovichhhhhhhhhhhhhhhhhhhhhhhhh");
        register.put("phone", "+7(777)777-77-77");
        register.put("role", Role.USER);
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(register.toString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void register_status_isNotValid_phone() throws Exception {
        usersRepository.deleteAll();
        JSONObject register = new JSONObject();
        register.put("username", "user@gmail.com");
        register.put("password", "password");
        register.put("firstName", "user");
        register.put("lastName", "userovich");
        register.put("phone", "+7(777)777-77-77-77");
        register.put("role", Role.USER);
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(register.toString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void login_status_NotValid_username() throws Exception {
        addToDb();
        JSONObject login = new JSONObject();
        login.put("username", "user@gmail.commmmmmmmmmmmmmmmmmmmm");
        login.put("password", "password");
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(login.toString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void login_status_NotValid_password() throws Exception {
        addToDb();
        JSONObject login = new JSONObject();
        login.put("username", "user@gmail.com");
        login.put("password", "passwordddddddddddddddddddddddd");
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(login.toString()))
                .andExpect(status().isBadRequest());
    }

}

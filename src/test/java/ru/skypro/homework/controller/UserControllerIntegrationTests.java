package ru.skypro.homework.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.Users;
import ru.skypro.homework.repository.UsersRepository;
import ru.skypro.homework.service.UserService;

import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTests {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private UserService userService;

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
        Users user = new Users(1,
                "user@gmail.com",
                "path-for-image",
                "user@gmail.com",
                "password",
                "ivan",
                "ivanov",
                "+7 777-77-77",
                Role.USER,
                new ArrayList<>());
        usersRepository.save(user);
    }

    @Test
    @WithMockUser(username = "user@gmail.com", roles = "USER", password = "password")
    public void getInformation_status_isOk() throws Exception {
        usersRepository.deleteAll();
        addToDb();
        mockMvc.perform(get("/users/me"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("user@gmail.com"));
    }

    @Test
    public void getInformation_status_throw401() throws Exception {
        usersRepository.deleteAll();
        addToDb();
        mockMvc.perform(get("/users/me"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "user@gmail.com", roles = "USER", password = "password")
    public void setPassword_status_isOk() throws Exception {
        usersRepository.deleteAll();
        addToDb();
        JSONObject newPassword = new JSONObject();
        newPassword.put("currentPassword", "password");
        newPassword.put("newPassword", "password1");
        mockMvc.perform(post("/users/set_password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newPassword.toString()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user@gmail.com", roles = "USER", password = "password")
    public void setPassword_status_isForbidden() throws Exception {
        usersRepository.deleteAll();
        addToDb();
        JSONObject newPassword = new JSONObject();
        newPassword.put("currentPassword", "password1");
        newPassword.put("newPassword", "password2");
        mockMvc.perform(post("/users/set_password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newPassword.toString()))
                .andExpect(status().isForbidden());
    }

    @Test
    public void setPassword_status_isUnauthorized() throws Exception {
        usersRepository.deleteAll();
        addToDb();
        JSONObject newPassword = new JSONObject();
        newPassword.put("currentPassword", "password1");
        newPassword.put("newPassword", "password2");
        mockMvc.perform(post("/users/set-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newPassword.toString()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void updateInformationAboutUser_status_isUnauthorized() throws Exception {
        usersRepository.deleteAll();
        addToDb();
        JSONObject updateUser = new JSONObject();
        updateUser.put("firstName", "ivan");
        updateUser.put("lastName", "ivanova");
        updateUser.put("phone", "+7(777)-777-77-77");
        mockMvc.perform(patch("/users/me")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateUser.toString()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "user@gmail.com", roles = "USER", password = "password")
    public void updateInformationAboutUser_status_isOk() throws Exception {
        usersRepository.deleteAll();
        addToDb();
        JSONObject updateUser = new JSONObject();
        updateUser.put("firstName", "ivan");
        updateUser.put("lastName", "ivanova");
        updateUser.put("phone", "+7(777)-777-77-77");
        mockMvc.perform(patch("/users/me")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateUser.toString()))
                .andExpect(status().isOk());
    }

//    @Test
//    @WithMockUser(username = "user@gmail.com", roles = "USER", password = "password")
//    public void updateImage_status_isOk() throws Exception {
//        usersRepository.deleteAll();
//        addToDb();
//        JSONObject image = new JSONObject();
//        image.put("newImage", "path-for-image");
//
//        mockMvc.perform(patch("/users/me/image")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(image.toString()))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void updateImage_status_isUnauthorized() throws Exception {
//        usersRepository.deleteAll();
//        addToDb();
//        JSONObject image = new JSONObject();
//        image.put("newImage", "path-for-image");
//
//        mockMvc.perform(patch("/users/me/image")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(image.toString()))
//                .andExpect(status().isUnauthorized());
//    }
}
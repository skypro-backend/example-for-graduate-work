package ru.skypro.homework;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void registerShouldCreateUser() throws Exception {
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"username\":\"user@gmail.com\","
                                + "\"password\":\"password\","
                                + "\"firstName\":\"User\","
                                + "\"lastName\":\"Userov\","
                                + "\"phone\":\"+7 999 888-77-66\","
                                + "\"role\":\"USER\""
                                + "}"))
                .andExpect(status().isCreated());
    }

    @Test
    void loginShouldUseUserFromDatabase() throws Exception {
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"username\":\"admin@gmail.com\","
                                + "\"password\":\"password\""
                                + "}"))
                .andExpect(status().isOk());
    }

    @Test
    void loginShouldReturnUnauthorizedForWrongPassword() throws Exception {
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"username\":\"admin@gmail.com\","
                                + "\"password\":\"wrongpass\""
                                + "}"))
                .andExpect(status().isUnauthorized());
    }
}

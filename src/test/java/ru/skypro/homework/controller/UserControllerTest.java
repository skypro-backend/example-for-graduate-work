package ru.skypro.homework.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser(username = "user@test.com")
    void getUser_Authenticated_ReturnsUser() throws Exception {
        User user = new User();
        user.setId(1);
        user.setEmail("user@test.com");
        user.setFirstName("Ivan");
        user.setLastName("Ivanov");
        user.setPhone("+79991234567");

        when(userService.getUser("user@test.com")).thenReturn(user);

        mockMvc.perform(get("/users/me")).andExpect(status().isOk()).andExpect(jsonPath("$.firstName").value("Ivan"));
    }

    @Test
    @WithMockUser(username = "user@test.com")
    void updateUser_ValidData_ReturnsUpdated() throws Exception {
        User updatedUser = new User();
        updatedUser.setId(1);
        updatedUser.setEmail("user@test.com");
        updatedUser.setFirstName("Petr");
        updatedUser.setLastName("Petrov");
        updatedUser.setPhone("+79997654321");

        when(userService.updateUser(eq("user@test.com"), any(UpdateUser.class))).thenReturn(updatedUser);

        mockMvc.perform(patch("/users/me").contentType(MediaType.APPLICATION_JSON).content("{" + "\"firstName\":\"Petr\"," + "\"lastName\":\"Petrov\"," + "\"phone\":\"+79997654321\"" + "}")).andExpect(status().isOk()).andExpect(jsonPath("$.firstName").value("Petr"));
    }

    @Test
    @WithMockUser(username = "user@test.com")
    void setPassword_ValidPassword_ChangesPassword() throws Exception {
        doNothing().when(userService).updatePassword(eq("user@test.com"), any(NewPassword.class));

        mockMvc.perform(post("/users/set_password").contentType(MediaType.APPLICATION_JSON).content("{" + "\"currentPassword\":\"password123\"," + "\"newPassword\":\"newpass123\"" + "}")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user@test.com")
    void updateUserImage_ValidImage_UpdatesImage() throws Exception {
        MockMultipartFile image = new MockMultipartFile("image", "image.jpg", "image/jpeg", "fake-image".getBytes());

        doNothing().when(userService).updateImage(eq("user@test.com"), any());

        mockMvc.perform(multipart("/users/me/image").file(image).with(request -> {
            request.setMethod("PATCH");
            return request;
        })).andExpect(status().isOk());
    }
}
package ru.skypro.homework.controller;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockPart;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.skypro.homework.dto.RoleDTO;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.PrimerService;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PasswordEncoder encoder;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private ImageRepository imageRepository;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    AuthService authService;
    @MockBean
    PrimerService primerService;

    @MockBean
    private Image image;




    @Test
    @WithMockUser(username = "authName")
    void setPassword() throws Exception {

        User user = new User();
        user.setUsername("Username");
        user.setPassword(encoder.encode("password"));

        User wrongUser = new User();
        wrongUser.setUsername("Username");
        wrongUser.setPassword(encoder.encode("wrongPassword"));

        JSONObject newPassword = new JSONObject();
        newPassword.put("currentPassword", "password");
        newPassword.put("newPassword", "wrongPassword");

        when(primerService.userExists("authName")).thenReturn(true);
        when(primerService.loadUserByUsername("authName")).thenReturn(user);
        when(primerService.findUser("authName")).thenReturn(user);
        when(userRepository.findByUsername(eq("authName"))).thenReturn(user);
        when(userRepository.save(any(User.class))).thenAnswer(input -> input.getArgument(0));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/set_password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newPassword.toString()))
                .andExpect(status().isOk());

        when(userRepository.findByUsername(eq("authName"))).thenReturn(wrongUser);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/set_password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newPassword.toString()))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "authName")
    void getUser() throws Exception {

        User user = new User();
        user.setId(222);
        user.setUsername("email@mail.ru");
        user.setPassword("password");
        user.setEmail("email@mail.ru");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setPhone("+79101234455");
        user.setRole(RoleDTO.USER);

        Image image = new Image();
        image.setId(444);

        when(userRepository.findByUsername(eq("authName"))).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/me"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(222))
                .andExpect(jsonPath("$.email").value("email@mail.ru"))
                .andExpect(jsonPath("$.firstName").value("firstName"))
                .andExpect(jsonPath("$.lastName").value("lastName"))
                .andExpect(jsonPath("$.phone").value("+79101234455"))
                .andExpect(jsonPath("$.role").value("USER"));

    }

    @Test
    @WithMockUser(username = "authName")
    void updateUser() throws Exception {

        User user = new User();
        user.setId(222);
        user.setUsername("email@mail.ru");
        user.setPassword("password");
        user.setEmail("email@mail.ru");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setPhone("+79101234455");
        user.setRole(RoleDTO.USER);

        Image image = new Image();
        image.setId(444);

        JSONObject userJson = new JSONObject();
        userJson.put("id", 222);
        userJson.put("email", "email@mail.ru");
        userJson.put("firstName", "newFirstName");
        userJson.put("lastName", "newLastName");
        userJson.put("phone", "newPhone");
        userJson.put("image", null);

        when(userRepository.findByUsername(eq("authName"))).thenReturn(user);
        when(userRepository.save(any(User.class))).thenAnswer(input -> input.getArgument(0));

        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/users/me")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("newFirstName"))
                .andExpect(jsonPath("$.lastName").value("newLastName"))
                .andExpect(jsonPath("$.phone").value("newPhone"));

    }

    @Test
    @WithMockUser(username = "authName")
    void updateUserImage() throws Exception {

        User user = new User();
        byte[] testImageBytes = Files.readAllBytes(Path.of("src/test/resources/phone.jpg"));
        MockPart testImagePart = new MockPart("image", "phone.jpg", testImageBytes);
        testImagePart.getHeaders().setContentType(MediaType.IMAGE_JPEG);

        Image image = new Image();
        image.setFilePath("\\images\\" + "phone.jpg");
        image.setFileSize(testImageBytes.length);
        image.setMediaType(MediaType.IMAGE_JPEG_VALUE);

        when(userRepository.findByUsername(eq("authName"))).thenReturn(user);
        when(userRepository.save(any(User.class))).thenAnswer(input -> input.getArgument(0));
        when(imageRepository.save(any(Image.class))).thenAnswer(input -> input.getArgument(0));

        mockMvc.perform(multipart(HttpMethod.PATCH, "/users/me/image")
                        .part(testImagePart))
                .andExpect(status().isOk());
    }
}
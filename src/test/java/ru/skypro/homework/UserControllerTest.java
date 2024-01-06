package ru.skypro.homework;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockPart;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.controller.UserController;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.impl.ImageServiceImpl;
import ru.skypro.homework.service.impl.UserServiceImpl;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = WebTestConfiguration.class)
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserServiceImpl userService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private ImageServiceImpl imageService;
    @MockBean
    private PasswordEncoder encoder;
    @InjectMocks
    private UserController userController;
    private UserEntity userEntity;
    private User user;

    @BeforeEach
    void init() {
        userEntity = new UserEntity(1,"user@gmail.com","name","lname",null,"user",Role.USER,null,null,null);
        user = new User(1,"user@gmail.com", "name", "lname", null, Role.USER, null);
    }

    @Test
    @Transactional
    @WithUserDetails(value = "user@gmail.com")
    public void setPasswordTest() throws Exception {
        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.of(userEntity));
        when(encoder.matches(any(String.class), any(String.class))).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/set_password")
                        .content(asJsonString(new NewPassword("user", "userUpdated")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @WithUserDetails(value = "user@gmail.com")
    public void getUserTest() throws Exception {
        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.of(userEntity));
        when(userMapper.userToUserDTO(any(UserEntity.class))).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/me")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.email").value(user.getEmail()))
                .andExpect(jsonPath("$.firstName").value(user.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(user.getLastName()))
                .andExpect(jsonPath("$.phone").value(user.getPhone()))
                .andExpect(jsonPath("$.role").value(user.getRole()))
                .andExpect(jsonPath("$.image").value(user.getImage()));
    }

    @Test
    @Transactional
    @WithUserDetails(value = "user@gmail.com")
    public void updateUserTest() throws Exception {
        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.of(userEntity));

        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/users/me")
                        .content(asJsonString(new UpdateUser("nameUpdated", "lnameUpdated", "+78967854746")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("nameUpdated"))
                .andExpect(jsonPath("$.lastName").value("lnameUpdated"))
                .andExpect(jsonPath("$.phone").value("+78967854746"));
    }

    @Test
    @Transactional
    @WithUserDetails(value = "user@gmail.com")
    public void updateImageTest() throws Exception {
        ImageEntity imageEntity = new ImageEntity(1,"file");

        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.of(userEntity));
        when(imageService.uploadImage(any(MultipartFile.class))).thenReturn(imageEntity);

        MockPart image = new MockPart("image","filename", "content".getBytes());
        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/users/me/image")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .with(request -> {
                            request.addPart(image);
                            return request;
                        }))
                .andExpect(status().isOk());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

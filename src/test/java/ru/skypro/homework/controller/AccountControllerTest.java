package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.skypro.homework.dto.account.NewPassword;
import ru.skypro.homework.dto.account.UpdateUser;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.TestService;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {
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
    @DisplayName("Обновление пароля")
    @WithMockUser(value = "testEmail@gmail.com")
    void shouldReturnOkWhenUpdatePasswordCalled() throws Exception {
        UserEntity userEntity = testService.createTestUser();
        NewPassword newPassword = new NewPassword();
        newPassword.setCurrentPassword("password");
        newPassword.setNewPassword("newTestPassword");
        mockMvc.perform(MockMvcRequestBuilders.post("/users/set_password")
                .content(objectMapper.writeValueAsString(newPassword))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
        Assertions.assertTrue(userRepository.findByEmail(userEntity.getEmail()).isPresent());
        Assertions.assertTrue(passwordEncoder.matches("newTestPassword",
                userRepository.findByEmail(userEntity.getEmail()).get().getPassword()));
    }

    @Test
    @DisplayName(value = "Получение информации об авторизованном пользователе")
    @WithMockUser(value = "testEmail@gmail.com")
    void shouldReturnInfoAboutUserWhenCalled() throws Exception {
        UserEntity userEntity = testService.createTestUser();
        mockMvc.perform(MockMvcRequestBuilders.get("/users/me"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("testEmail@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("testFirstName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.LastName").value("testLastName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("+77777777777"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.image").value("/users/image/" + userEntity.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.role").value(userEntity.getRole().name()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Обновление информации об авторизованном пользователе")
    @WithMockUser(value = "testEmail@gmail.com")
    void shouldReturnUpdatedInfoAboutUserWhenCalled() throws Exception {
        testService.createTestUser();
        UpdateUser updatedUser = new UpdateUser();
        updatedUser.setFirstName("updatedFirstName");
        updatedUser.setLastName("updatedLastName");
        updatedUser.setPhone("+795555555555");
        mockMvc.perform(MockMvcRequestBuilders.patch("/users/me")
                        .content(objectMapper.writeValueAsString(updatedUser))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("updatedFirstName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("updatedLastName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("+795555555555"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName(value = "Обновление аватара авторизованного пользователя")
    @WithMockUser(value = "testEmail@gmail.com")
    void shouldReturnOkWhenUpdateUserAvatarCalled() throws Exception {
        UserEntity userEntity = testService.createTestUser();
        MockMultipartFile image = new MockMultipartFile(
                "image", "image", MediaType.MULTIPART_FORM_DATA_VALUE, "image".getBytes()
        );
        mockMvc.perform(multipart(HttpMethod.PATCH, "/users/me/image")
                .file(image))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
        Assertions.assertTrue(userRepository.findById(userEntity.getId()).isPresent());

    }

    @Test
    @DisplayName(value = "Получение аватара пользователя по его id")
    @WithMockUser
    void shouldReturnImageByteArrayWhenCalled() throws Exception {
        UserEntity userEntity = testService.createTestUser();
        mockMvc.perform(MockMvcRequestBuilders.get("/users/image/{userId}", userEntity.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("/users/image/" + userEntity.getId())) // not sure
                .andExpect(status().isOk());
    }
}

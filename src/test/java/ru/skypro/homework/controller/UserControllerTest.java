package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.impl.UserServiceImpl;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link UserController#updateUser(UpdateUserDTO)}
     */
    @Test
    void testUpdateUser() throws Exception {
        when(userService.updateUser(Mockito.<UpdateUserDTO>any())).thenReturn(new UpdateUserDTO());

        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.firstName("Jane");
        updateUserDTO.lastName("Doe");
        updateUserDTO.phone("6625550144");
        updateUserDTO.setFirstName("Jane");
        updateUserDTO.setLastName("Doe");
        updateUserDTO.setPhone("6625550144");
        String content = (new ObjectMapper()).writeValueAsString(updateUserDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/users/me")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"firstName\":null,\"lastName\":null,\"phone\":null}"));
    }

    /**
     * Method under test: {@link UserController#updateUser(UpdateUserDTO)}
     */
    @Test
    void testUpdateUser2() throws Exception {
        when(userService.updateUser(Mockito.<UpdateUserDTO>any())).thenReturn(null);

        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.firstName("Jane");
        updateUserDTO.lastName("Doe");
        updateUserDTO.phone("6625550144");
        updateUserDTO.setFirstName("Jane");
        updateUserDTO.setLastName("Doe");
        updateUserDTO.setPhone("6625550144");
        String content = (new ObjectMapper()).writeValueAsString(updateUserDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/users/me")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link UserController#getUser()}
     */
    @Test
    void testGetUser() throws Exception {
        when(userService.getCurrentUser()).thenReturn(new UserDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/me");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"email\":null,\"firstName\":null,\"lastName\":null,\"phone\":null,\"role\":null,\"image\":null}"));
    }

    /**
     * Method under test: {@link UserController#loadUserImage(MultipartFile)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testLoadUserImage() throws IOException {

        UserController userController = new UserController(
                new UserServiceImpl(new Argon2PasswordEncoder(), mock(UserRepository.class)));
        userController.loadUserImage(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
    }

    /**
     * Method under test: {@link UserController#setUserPassword(NewPasswordDTO)}
     */
    @Test
    void testSetUserPassword() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/users/set_password")
                .contentType(MediaType.APPLICATION_JSON);

        NewPasswordDTO newPasswordDTO = new NewPasswordDTO();
        newPasswordDTO.currentPassword("iloveyou");
        newPasswordDTO.newPassword("iloveyou");
        newPasswordDTO.setCurrentPassword("iloveyou");
        newPasswordDTO.setNewPassword("iloveyou");

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(newPasswordDTO));
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(userController).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        MvcResult mvcResult = actualPerformResult.andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status, "Expected HTTP status 200");
        String content = mvcResult.getResponse().getContentAsString();
        assertNotNull(content, "Expected non-null response content");
    }

}

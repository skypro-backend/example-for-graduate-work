package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.service.UserService;

import java.io.InputStream;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    /**
     * Method under test:  {@link UserController#updateUser(UpdateUserDTO)}
     */
    @Test
    void testUpdateUser() throws Exception {
        when(userService.updateUser(Mockito.<UpdateUserDTO>any())).thenReturn(new UserDTO());

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
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"email\":null,\"firstName\":null,\"lastName\":null,\"phone\":null,\"role\":null,\"image\":null}"));
    }

    /**
     * Method under test:  {@link UserController#updateUser(UpdateUserDTO)}
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
        actualPerformResult.andExpect(status().isNotFound());
    }

    /**
     * Method under test:  {@link UserController#getUser()}
     */
    @Test
    void testGetUser() throws Exception {
        when(userService.getCurrentUser()).thenReturn(new UserDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/me");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"email\":null,\"firstName\":null,\"lastName\":null,\"phone\":null,\"role\":null,\"image\":null}"));
    }

    /**
     * Method under test:  {@link UserController#getUser()}
     */
    @Test
    void testGetUser2() throws Exception {
        when(userService.getCurrentUser()).thenReturn(new UserDTO());
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder);
        actualPerformResult.andExpect(status().isNotFound());
    }

    /**
     * Method under test: {@link UserController#loadUserImage(MultipartFile)}
     */
    @Test
    void testLoadUserImage() throws Exception {
        MockHttpServletRequestBuilder patchResult = MockMvcRequestBuilders.patch("/users/me/image");
        MockHttpServletRequestBuilder requestBuilder = patchResult.param("image",
                String.valueOf(new MockMultipartFile("Name", (InputStream) null)));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder);
        actualPerformResult.andExpect(status().is(415));
    }
}

package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.skypro.homework.dto.LoginDTO;
import ru.skypro.homework.dto.RegisterDTO;
import ru.skypro.homework.model.Role;
import ru.skypro.homework.service.AuthService;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {AuthController.class})
@ExtendWith(SpringExtension.class)
class AuthControllerTest {
    @Autowired
    private AuthController authController;

    @MockBean
    private AuthService authService;

    /**
     * Method under test: {@link AuthController#login(LoginDTO)}
     */
    @Test
    void testLogin() throws Exception {
        when(authService.login(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.password("iloveyou");
        loginDTO.setPassword("iloveyou");
        loginDTO.setUsername("janedoe");
        loginDTO.username("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(loginDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link AuthController#login(LoginDTO)}
     */
    @Test
    void testLogin2() throws Exception {
        when(authService.login(Mockito.<String>any(), Mockito.<String>any())).thenReturn(false);

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.password("iloveyou");
        loginDTO.setPassword("iloveyou");
        loginDTO.setUsername("janedoe");
        loginDTO.username("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(loginDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(401));
    }

    /**
     * Method under test: {@link AuthController#register(RegisterDTO)}
     */
    @Test
    void testRegister() throws Exception {
        when(authService.register(Mockito.<RegisterDTO>any())).thenReturn(true);

        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.firstName("Jane");
        registerDTO.lastName("Doe");
        registerDTO.password("iloveyou");
        registerDTO.phone("6625550144");
        registerDTO.role(Role.USER);
        registerDTO.setFirstName("Jane");
        registerDTO.setLastName("Doe");
        registerDTO.setPassword("iloveyou");
        registerDTO.setPhone("6625550144");
        registerDTO.setRole(Role.USER);
        registerDTO.setUsername("janedoe");
        registerDTO.username("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(registerDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    /**
     * Method under test: {@link AuthController#register(RegisterDTO)}
     */
    @Test
    void testRegister2() throws Exception {
        when(authService.register(Mockito.<RegisterDTO>any())).thenReturn(false);

        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.firstName("Jane");
        registerDTO.lastName("Doe");
        registerDTO.password("iloveyou");
        registerDTO.phone("6625550144");
        registerDTO.role(Role.USER);
        registerDTO.setFirstName("Jane");
        registerDTO.setLastName("Doe");
        registerDTO.setPassword("iloveyou");
        registerDTO.setPhone("6625550144");
        registerDTO.setRole(Role.USER);
        registerDTO.setUsername("janedoe");
        registerDTO.username("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(registerDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

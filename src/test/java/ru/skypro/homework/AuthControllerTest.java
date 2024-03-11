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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.skypro.homework.controller.AuthController;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.AuthServiceImpl;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = WebTestConfiguration.class)
@AutoConfigureMockMvc
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AuthServiceImpl authService;
    @MockBean
    private PasswordEncoder encoder;
    @MockBean
    private UserRepository userRepository;
    @InjectMocks
    private AuthController authController;
    private UserEntity userEntity;

    @BeforeEach
    void init() {
        userEntity = new UserEntity(1,"user@gmail.com","name","lname",null,"user",Role.USER,null,null,null);
    }

    @Test
    @Transactional
    @WithUserDetails(value = "user@gmail.com")
    public void loginTest() throws Exception {
        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.of(userEntity));
        when(encoder.matches(any(String.class), any(String.class))).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/login")
                        .content(asJsonString(new Login("user@gmail.com", "user")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @WithUserDetails(value = "user@gmail.com")
    public void registerTest() throws Exception {
        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/register")
                        .content(asJsonString(new Register("user1@gmail.com", "user1", "name", "lname", null, Role.USER)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

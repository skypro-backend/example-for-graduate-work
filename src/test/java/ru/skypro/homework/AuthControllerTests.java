package ru.skypro.homework;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.skypro.homework.dto.LoginReq;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.enums.Role;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.UserRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AuthControllerTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @AfterEach
    void cleanUp() {
        userRepository.deleteAll();
    }

    @Test
    void register_whenAllIsCorrect() throws Exception {
        RegisterReq req = new RegisterReq();
        req.setUsername("test@test.ru");
        req.setPassword("password");
        req.setFirstName("test1");
        req.setLastName("test2");
        req.setPhone("+79099099999");

        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }

    @Test
    void register_whenBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/register"))
                .andExpect(status().is(400));
    }

    @Test
    void login_whenAllIsCorrect() throws Exception {
        UserModel user = new UserModel();
        user.setRole(Role.USER);
        user.setFirstName("FIRST_NAME");
        user.setLastName("LAST_NAME");
        user.setPhone("+79099099999");
        user.setUsername("test123@test.ru");
        user.setPassword(encoder.encode("PASSWORD"));
        userRepository.save(user);

        LoginReq req = new LoginReq();
        req.setUsername("test123@test.ru");
        req.setPassword("PASSWORD");

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }
}

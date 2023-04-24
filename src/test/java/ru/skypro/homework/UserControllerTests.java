package ru.skypro.homework;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockPart;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.skypro.homework.dto.enums.Role;
import ru.skypro.homework.dto.user.User;
import ru.skypro.homework.model.AdsModel;
import ru.skypro.homework.model.CommentModel;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.MyUserDetailsManager;

import java.time.Instant;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserControllerTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdsRepository adsRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private MyUserDetailsManager myUserDetailsManager;
    private final UserModel user = new UserModel();
    private final AdsModel ads = new AdsModel();
    private final CommentModel comment = new CommentModel();
    private final MockPart imageFile = new MockPart("image", "image", "image".getBytes());
    private static final String FIRST_NAME = "first";
    private static final String LAST_NAME = "last";
    private static final String PHONE = "+79099099999";
    private static final String USERNAME = "test@test.ru";
    private static final String PASSWORD = "password";
    private static final Instant CREATED_AT = Instant.now();


    private static Authentication authentication;

    @BeforeEach
    void setUp() {
        user.setRole(Role.USER);
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setPhone(PHONE);
        user.setUsername(USERNAME);
        user.setPassword(encoder.encode(PASSWORD));
        userRepository.save(user);

        ads.setUser(user);
        ads.setPrice(555);
        ads.setTitle("ADS_TITLE");
        ads.setDescription("ADS_DESC");
        adsRepository.save(ads);

        comment.setText("COMM_TEXT");
        comment.setUser(user);
        comment.setAds(ads);
        comment.setCreatedAt(CREATED_AT);
        commentRepository.save(comment);

        UserDetails userDetails = myUserDetailsManager.loadUserByUsername(USERNAME);
        authentication = new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthorities());

    }

    @AfterEach
    void deleteObject() {
        userRepository.deleteAll();
    }

    @Before
    public void setUp2() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    void setPassword_whenAllIsCorrect() throws Exception {
        JSONObject jsonNewPassword = new JSONObject();
        jsonNewPassword.put("currentPassword", PASSWORD);
        jsonNewPassword.put("newPassword", "password123");

        this.mockMvc.perform(MockMvcRequestBuilders.post("/users/set_password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonNewPassword.toString())
                        .with(authentication(authentication)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPassword").value(jsonNewPassword.getString("currentPassword")))
                .andExpect(jsonPath("$.newPassword").value(jsonNewPassword.getString("newPassword")));
    }

    @Test
    void setPassword_whenStatus401() throws Exception {
        JSONObject jsonNewPassword = new JSONObject();
        jsonNewPassword.put("currentPassword", PASSWORD);
        jsonNewPassword.put("newPassword", "password123");

        mockMvc.perform(MockMvcRequestBuilders.post("/users/set_password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonNewPassword.toString())
                )
                .andExpect(status().is(401));
    }

    @Test
    void setPassword_whenStatus403() throws Exception {
        JSONObject jsonNewPassword = new JSONObject();
        jsonNewPassword.put("currentPassword", "password16");
        jsonNewPassword.put("newPassword", "password123");

        mockMvc.perform(MockMvcRequestBuilders.post("/users/set_password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonNewPassword.toString())
                        .with(authentication(authentication)))
                .andExpect(status().is(403));
    }

    @Test
    void setPassword_whenStatus404() throws Exception {
        userRepository.deleteAll();

        JSONObject jsonNewPassword = new JSONObject();
        jsonNewPassword.put("currentPassword", PASSWORD);
        jsonNewPassword.put("newPassword", "password123");

        mockMvc.perform(MockMvcRequestBuilders.post("/users/set_password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonNewPassword.toString())
                        .with(authentication(authentication)))
                .andExpect(status().is(404));
    }

    @Test
    void getUserInfo_whenAllIsCorrect() throws Exception {
        mockMvc.perform(get("/users/me")
                        .with(authentication(authentication)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(USERNAME))
                .andExpect(jsonPath("$.firstName").value(FIRST_NAME))
                .andExpect(jsonPath("$.lastName").value(LAST_NAME))
                .andExpect(jsonPath("$.phone").value(PHONE));
    }

    @Test
    void getUserInfo_whenStatus401() throws Exception {
        mockMvc.perform(get("/users/me"))
                .andExpect(status().is(401));
    }

    @Test
    void updateUser_whenAllIsCorrect() throws Exception {
        Integer id = userRepository.findByUsername(USERNAME).get().getPk();
        User testUser = new User();
        testUser.setFirstName("PEPE");
        testUser.setLastName("THE FROG");
        testUser.setId(id);
        testUser.setEmail(USERNAME);
        testUser.setPhone(PHONE);

        mockMvc.perform(patch("/users/me")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser))
                        .with(authentication(authentication)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(testUser.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(testUser.getLastName()));
    }

    @Test
    void updateUser_whenStatus401() throws Exception {
        Integer id = userRepository.findByUsername(USERNAME).get().getPk();
        User testUser = new User();
        testUser.setFirstName("PEPE");
        testUser.setLastName("THE FROG");
        testUser.setId(id);
        testUser.setEmail(USERNAME);
        testUser.setPhone(PHONE);

        mockMvc.perform(patch("/users/me")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().is(401));
    }

    @Test
    void updateUser_whenStatus404() throws Exception {
        User testUser = new User();
        testUser.setFirstName("PEPE");
        testUser.setLastName("THE FROG");
        testUser.setId(5);
        testUser.setEmail(USERNAME);
        testUser.setPhone(PHONE);

        mockMvc.perform(patch("/users/me")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser))
                        .with(authentication(authentication)))
                .andExpect(status().is(404));
    }

    @Test
    void updateUserImage_whenAllIsCorrect() throws Exception {
        mockMvc.perform(patch("/users/me/image")
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .with(request -> {
                            request.addPart(imageFile);
                            return request;
                        })
                        .with(authentication(authentication)))
                .andExpect(status().isOk())
                .andReturn();
    }


}

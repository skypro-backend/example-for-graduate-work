package ru.skypro.homework;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import ru.skypro.homework.dto.ads.Ads;
import ru.skypro.homework.dto.ads.CreateAds;
import ru.skypro.homework.dto.ads.ResponseWrapperAds;
import ru.skypro.homework.dto.enums.Role;
import ru.skypro.homework.model.AdsModel;
import ru.skypro.homework.model.CommentModel;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.MyUserDetailsManager;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.Instant;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AdsControllerTests {
    @Autowired
    private AdsRepository adsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MyUserDetailsManager myUserDetailsManager;
    private Authentication authentication;
    private final UserModel user = new UserModel();
    private final AdsModel ads = new AdsModel();
    private final CommentModel commentModel = new CommentModel();

    @BeforeEach
    void setUp() {
        adsRepository.deleteAll();
        commentRepository.deleteAll();

        user.setFirstName("first");
        user.setLastName("last");
        user.setPhone("+79099099999");
        user.setUsername("test@test.ru");
        user.setPassword(encoder.encode("password"));
        user.setRole(Role.USER);
        userRepository.save(user);

        ads.setPrice(555);
        ads.setTitle("testTitle");
        ads.setDescription("testDesc");
        ads.setUser(user);
        adsRepository.save(ads);

        commentModel.setText("testText");
        commentModel.setAds(ads);
        commentModel.setUser(user);
        commentModel.setCreatedAt(Instant.now());
        commentModel.setAds(ads);
        commentRepository.save(commentModel);

        UserDetails userDetails = myUserDetailsManager
                .loadUserByUsername(user.getUsername());
        authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthorities());

    }

    @AfterEach
    void cleanUp() {
        userRepository.deleteAll();
        adsRepository.deleteAll();
        commentRepository.deleteAll();
    }

    @Test
    void getAllAds_whenAllIsCorrect() throws Exception {
        ResponseWrapperAds rwa = new ResponseWrapperAds();
        rwa.setCount(1);
        rwa.setResults(List.of(Ads.fromModel(ads)));

        mockMvc.perform(get("/ads"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(rwa.getCount()));
    }

    @Test
    void getAds_whenAllIsCorrect() throws Exception {
        mockMvc.perform(get("/ads/" + ads.getId())
                        .with(authentication(authentication)))
                .andExpect(status().isOk());
    }

    @Test
    void getAds_whenStatus404() throws Exception {
        adsRepository.deleteAll();
        mockMvc.perform(get("/ads/" + ads.getId())
                        .with(authentication(authentication)))
                .andExpect(status().is(404));
    }

    @Test
    void updateAds_whenAllIsCorrect() throws Exception {
        CreateAds createAds = new CreateAds();
        createAds.setTitle("newTitle");
        createAds.setDescription("newDescription");
        createAds.setPrice(999);

        mockMvc.perform(patch("/ads/" + ads.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createAds))
                        .with(authentication(authentication)))
                .andExpect(status().isOk());
    }

    @Test
    void updateAds_whenStatus401() throws Exception {
        CreateAds createAds = new CreateAds();
        createAds.setTitle("newTitle");
        createAds.setDescription("newDescription");
        createAds.setPrice(999);

        mockMvc.perform(patch("/ads/" + ads.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createAds)))
                .andExpect(status().is(401));
    }

    @Test
    void updateAds_whenStatus404() throws Exception {
        adsRepository.deleteAll();
        CreateAds createAds = new CreateAds();
        createAds.setTitle("newTitle");
        createAds.setDescription("newDescription");
        createAds.setPrice(999);

        mockMvc.perform(patch("/ads/" + ads.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createAds))
                        .with(authentication(authentication)))
                .andExpect(status().is(404));
    }

    @Test
    void deleteAds_whenAllIsCorrect() throws Exception {
        mockMvc.perform(delete("/ads/" + ads.getId())
                        .with(authentication(authentication)))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteAds_whenStatus404() throws Exception {
        mockMvc.perform(delete("/ads/" + (ads.getId() + 1))
                        .with(authentication(authentication)))
                .andExpect(status().is(404));
    }

    @Test
    void deleteAds_whenStatus401() throws Exception {
        mockMvc.perform(delete("/ads/" + ads.getId()))
                .andExpect(status().is(401));
    }

    @Test
    void deleteAds_whenStatus403() throws Exception {
        UserModel user2 = new UserModel();
        user2.setUsername("test123@test.com");
        user2.setFirstName("firstN");
        user2.setLastName("lastN");
        user2.setPhone("+79099877654");
        user2.setRole(Role.USER);
        user2.setPassword("qwertyytrewq");
        userRepository.save(user2);

        AdsModel ads2 = new AdsModel();
        ads2.setUser(user2);
        ads2.setDescription("shsughb");
        ads2.setTitle("huiyhae");
        ads2.setPrice(2345);
        adsRepository.save(ads2);

        mockMvc.perform(delete("/ads/" + ads2.getId())
                        .with(authentication(authentication)))
                .andExpect(status().is(403));
    }

    @Test
    void getAdsMe_whenAllIsCorrect() throws Exception {
        mockMvc.perform(get("/ads/me")
                        .with(authentication(authentication)))
                .andExpect(status().isOk());
    }

    @Test
    void getAdsMe_whenStatus401() throws Exception {
        mockMvc.perform(get("/ads/me"))
                .andExpect(status().is(401));
    }

    @Test
    void updateImage_whenStatus401() throws Exception {
        mockMvc.perform(patch("/ads/" + ads.getId() + "/image"))
                .andExpect(status().is(401));
    }

    @Test
    void getComments_whenAllIsCorrect() throws Exception {
        mockMvc.perform(get("/ads/" + ads.getId() + "/comments")
                        .with(authentication(authentication)))
                .andExpect(status().isOk());

    }
}

package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Role;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class AdsControllerIntegrationTest {

    private static final String RAW_PASSWORD = "password123";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    private User owner;
    private User otherUser;
    private User admin;

    @BeforeEach
    void setUp() {
        owner = createUser("owner@mail.com", Role.USER);
        otherUser = createUser("other@mail.com", Role.USER);
        admin = createUser("admin@mail.com", Role.ADMIN);
    }

    @Test
    void getAllAds_withoutAuthentication_shouldReturn200() throws Exception {
        mockMvc.perform(get("/ads"))
                .andExpect(status().isOk());
    }

    @Test
    void addAd_withoutAuthentication_shouldReturn401() throws Exception {
        CreateOrUpdateAd properties = new CreateOrUpdateAd();
        properties.setTitle("Новое объявление");
        properties.setPrice(500);
        properties.setDescription("Описание нового объявления");

        MockMultipartFile propertiesPart = new MockMultipartFile(
                "properties", "", "application/json", objectMapper.writeValueAsBytes(properties));
        MockMultipartFile imagePart = new MockMultipartFile(
                "image", "test.jpg", "image/jpeg", "fake-image-content".getBytes());

        mockMvc.perform(multipart("/ads").file(propertiesPart).file(imagePart))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void removeAd_asAnotherUser_shouldReturn403() throws Exception {
        Ad ad = createAd(owner);

        mockMvc.perform(delete("/ads/{id}", ad.getId())
                        .with(httpBasic(otherUser.getEmail(), RAW_PASSWORD)))
                .andExpect(status().isForbidden());
    }

    @Test
    void removeAd_asOwner_shouldReturn204() throws Exception {
        Ad ad = createAd(owner);

        mockMvc.perform(delete("/ads/{id}", ad.getId())
                        .with(httpBasic(owner.getEmail(), RAW_PASSWORD)))
                .andExpect(status().isNoContent());
    }

    @Test
    void removeAd_asAdmin_shouldReturn204() throws Exception {
        Ad ad = createAd(owner);

        mockMvc.perform(delete("/ads/{id}", ad.getId())
                        .with(httpBasic(admin.getEmail(), RAW_PASSWORD)))
                .andExpect(status().isNoContent());
    }

    private User createUser(String email, Role role) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(RAW_PASSWORD));
        user.setFirstName("Test");
        user.setLastName("User");
        user.setRole(role);
        return userRepository.save(user);
    }

    private Ad createAd(User author) {
        Ad ad = new Ad();
        ad.setTitle("Тестовое объявление");
        ad.setPrice(1000);
        ad.setDescription("Описание тестового объявления");
        ad.setAuthor(author);
        ad.setImage("/images/ad_test.jpg");
        return adRepository.save(ad);
    }
}

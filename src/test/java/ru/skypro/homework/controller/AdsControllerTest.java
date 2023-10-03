package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.PersonalizedUserInformationServiceImpl;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AdsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AdsRepository adsRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PersonalizedUserInformationServiceImpl userInformationService;
    @Autowired
    private ImageRepository imageRepository;

    private Authentication authentication;
    private final MockPart imageFile = new MockPart("image", "filename", "image".getBytes());
    private final User user = new User();
    private final CreateOrUpdateAd createAds = new CreateOrUpdateAd();
    private final Ads ads = new Ads();
    private final Image image = new Image();


    @BeforeEach
    void setUp() {
        user.setUserName("USERNAME@GMAIL.COM");
        user.setFirstName("FIRSTNAME");
        user.setLastName("LASTNAME");
        user.setPhone("+79999999999");
        user.setPassword(passwordEncoder.encode("PASSWORD"));
        user.setRole(Role.USER);
        userRepository.save(user);

        UserDetails userDetails = userInformationService.loadUserByUsername(user.getUserName());
        authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                userDetails.getPassword(), userDetails.getAuthorities());

        ads.setTitle("ADS");
        ads.setDescription("DESC");
        ads.setPrice(2000);
        ads.setAuthor(user);
        adsRepository.save(ads);
    }

    @AfterEach
    void cleanUp() {
        userRepository.delete(user);
    }

    @Test
    public void testGetAllAdsReturnsCorrectAdsList() throws Exception {

        mockMvc.perform(get("/ads").with(authentication(authentication)))
                .andExpect(status().isOk()).andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.count").isNumber())
                .andExpect(jsonPath("$.results").isArray());
    }

    @Test
    public void testGetInformationAndReturnCorrectAds() throws Exception {

        mockMvc.perform(get("/ads/{id}", ads.getId()).with(authentication(authentication)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pk").value(ads.getId()))
                .andExpect(jsonPath("$.email").value(user.getUserName()))
                .andExpect(jsonPath("$.authorFirstName").value(user.getFirstName()))
                .andExpect(jsonPath("$.authorLastName").value(user.getLastName()))
                .andExpect(jsonPath("$.phone").value(user.getPhone()))
                .andExpect(jsonPath("$.title").value(ads.getTitle()))
                .andExpect(jsonPath("$.description").value(ads.getDescription()))
                .andExpect(jsonPath("$.price").value(ads.getPrice()));
    }

    @Test
    public void testRemoveAdsWhenRemove() throws Exception {

        mockMvc.perform(delete("/ads/{id}", ads.getId()).with(authentication(authentication)))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateAndReturnAds() throws Exception {
        String newTitle = "New Ads";
        String newDesc = "New Description";
        Integer newPrice = 2000;
        ads.setTitle(newTitle);
        ads.setDescription(newDesc);
        ads.setPrice(newPrice);
        adsRepository.save(ads);

        mockMvc.perform(patch("/ads/{id}", ads.getId()).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ads)).with((authentication(authentication))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(newTitle))
                .andExpect(jsonPath("$.description").value(newDesc))
                .andExpect(jsonPath("$.price").value(newPrice));
    }

    @Test
    public void testGetAdsAndReturnAdsList() throws Exception {

        mockMvc.perform(get("/ads/me").with(authentication(authentication)))
                .andExpect(status().isOk()).andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.count").isNumber())
                .andExpect(jsonPath("$.results").isArray());
    }

    @Test
    public void testUpdateAdsImage() throws Exception {

        mockMvc.perform(patch("/ads/{id}/image", ads.getId()).contentType(MediaType.MULTIPART_FORM_DATA_VALUE).with(request -> {
            request.addPart(imageFile);
            return request;
        }).with(authentication(authentication))).andExpect(status().isOk());
    }

    @Test
    public void testGetImage() throws Exception {
        image.setData("image".getBytes());
        image.setMediaType("image/jpeg");
        imageRepository.save(image);
        ads.setImage(image);
        adsRepository.save(ads);

        mockMvc.perform(get("/ads/image/{id}", image.getId()).
                        contentType(MediaType.MULTIPART_FORM_DATA_VALUE).with(authentication(authentication)))
                .andExpect(status().isOk()).andExpect(content().bytes(image.getData()));
    }
}
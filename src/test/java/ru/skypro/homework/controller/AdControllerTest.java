package ru.skypro.homework.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.service.AdService;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdController.class)
public class AdControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdService adService;

    private Ads ads;
    private Ad ad;
    private CreateOrUpdateAd createOrUpdateAd;

    @BeforeEach
    void setUp() {
        ad = new Ad();
        ad.setPk(1);
        ad.setAuthor(1);
        ad.setTitle("Laptop");
        ad.setPrice(1000);
        ad.setImage("/ads/1/image");

        ads = new Ads();
        ads.setCount(1);
        ads.setResults(Collections.singletonList(ad));

        createOrUpdateAd = new CreateOrUpdateAd();
        createOrUpdateAd.setTitle("Laptop");
        createOrUpdateAd.setPrice(1000);
        createOrUpdateAd.setDescription("Good condition");
    }

    @Test
    void getAllAds_Unauthorized_ReturnsAds() throws Exception {
        when(adService.getAllAds()).thenReturn(ads);

        mockMvc.perform(get("/ads")).andExpect(status().isOk()).andExpect(jsonPath("$.count").value(1)).andExpect(jsonPath("$.results[0].title").value("Laptop"));
    }

    @Test
    @WithMockUser
    void getAdsMe_Authenticated_ReturnsUserAds() throws Exception {
        when(adService.getAdsByUser("user@test.com")).thenReturn(ads);

        mockMvc.perform(get("/ads/me")).andExpect(status().isOk()).andExpect(jsonPath("$.count").value(1));
    }

    @Test
    @WithMockUser(username = "user@test.com")
    void addAd_ValidData_ReturnsCreated() throws Exception {
        when(adService.createAd(any(), eq("user@test.com"))).thenReturn(ad);

        String json = "{" + "\"title\":\"Laptop\"," + "\"price\":1000," + "\"description\":\"Good condition\"" + "}";

        MockMultipartFile image = new MockMultipartFile("image", "image.jpg", "image/jpeg", "fake-image".getBytes());
        MockMultipartFile properties = new MockMultipartFile("properties", "", "application/json", json.getBytes());

        mockMvc.perform(multipart("/ads").file(image).file(properties)).andExpect(status().isCreated()).andExpect(jsonPath("$.title").value("Laptop"));
    }

    @Test
    void addAd_Unauthenticated_ReturnsUnauthorized() throws Exception {
        String json = "{" + "\"title\":\"Laptop\"," + "\"price\":1000," + "\"description\":\"Good condition\"" + "}";

        MockMultipartFile image = new MockMultipartFile("image", "image.jpg", "image/jpeg", "fake".getBytes());
        MockMultipartFile properties = new MockMultipartFile("properties", "", "application/json", json.getBytes());

        mockMvc.perform(multipart("/ads").file(image).file(properties)).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "user@test.com")
    void getAd_Exists_ReturnsExtendedAd() throws Exception {
        var extendedAd = new ru.skypro.homework.dto.ExtendedAd();
        extendedAd.setPk(1);
        extendedAd.setTitle("Laptop");
        extendedAd.setPrice(1000);

        when(adService.getExtendedAd(1)).thenReturn(extendedAd);

        mockMvc.perform(get("/ads/1")).andExpect(status().isOk()).andExpect(jsonPath("$.pk").value(1));
    }

    @Test
    @WithMockUser(username = "user@test.com")
    void updateAd_ByAuthor_Success() throws Exception {
        when(adService.updateAd(eq(1), any(), eq("user@test.com"))).thenReturn(ad);

        String json = "{" + "\"title\":\"Updated\"," + "\"price\":1200," + "\"description\":\"Like new\"" + "}";

        mockMvc.perform(patch("/ads/1").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andExpect(jsonPath("$.title").value("Laptop"));
    }

    @Test
    @WithMockUser(username = "admin@test.com", roles = "ADMIN")
    void deleteAd_ByAdmin_Success() throws Exception {
        doNothing().when(adService).deleteAd(1, "admin@test.com");

        mockMvc.perform(delete("/ads/1")).andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "user@test.com")
    void updateImage_ByAuthor_Success() throws Exception {
        when(adService.updateImage(eq(1), any())).thenReturn(ad);

        MockMultipartFile image = new MockMultipartFile("image", "image.jpg", "image/jpeg", "fake".getBytes());

        mockMvc.perform(multipart("/ads/1/image").file(image).with(request -> {
            request.setMethod("PATCH");
            return request;
        })).andExpect(status().isOk());
    }
}
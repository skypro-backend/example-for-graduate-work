package ru.skypro.homework.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.service.AdService;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class AdsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdService adService;

    @Test
    void getAllAds_ShouldBeAccessibleToUnauthenticatedUsers() throws Exception {
        Ads ads = new Ads();
        ads.setCount(0);
        ads.setResults(Collections.emptyList());

        when(adService.getAllAds()).thenReturn(ads);

        mockMvc.perform(get("/ads"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(0));
    }

    @Test
    @WithMockUser(username = "user@test.com")
    void getAdsMe_WithAuth_ShouldReturnOk() throws Exception {
        when(adService.getAdsMe("user@test.com")).thenReturn(new Ads());

        mockMvc.perform(get("/ads/me"))
                .andExpect(status().isOk());
    }
}
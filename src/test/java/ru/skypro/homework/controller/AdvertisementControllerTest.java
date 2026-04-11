package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.security.TestSecurityConfig;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({AdvertisementController.class, CommentController.class})
@Import(TestSecurityConfig.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@DisplayName("Тесты объявлений и комментариев")
class AdvertisementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AdService adService;

    @MockBean
    private CommentService commentService;

    @Test
    @WithMockUser(username = "user@example.com")
    @DisplayName("GET /ads - получение всех объявлений")
    void getAllAds_ReturnsOk() throws Exception {
        Ads ads = Ads.builder()
                .count(0)
                .results(Collections.emptyList())
                .build();

        when(adService.getAllAds()).thenReturn(ads);

        mockMvc.perform(get("/ads"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user@example.com")
    @DisplayName("GET /ads/{id} - получение объявления по ID")
    void getAdById_ValidId_ReturnsOk() throws Exception {
        ExtendedAd extendedAd = ExtendedAd.builder()
                .pk(1)
                .title("Test Ad")
                .price(1000)
                .build();

        when(adService.getAdById(1)).thenReturn(extendedAd);

        mockMvc.perform(get("/ads/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user@example.com")
    @DisplayName("POST /ads - создание объявления с изображением")
    void createAd_WithImage_ReturnsCreated() throws Exception {
        MockMultipartFile imageFile = new MockMultipartFile(
                "image",
                "test.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                "test image content".getBytes()
        );

        MockMultipartFile propertiesFile = new MockMultipartFile(
                "properties",
                "",
                MediaType.APPLICATION_JSON_VALUE,
                "{\"title\":\"Test Ad\",\"price\":1000,\"description\":\"Test Description\"}".getBytes()
        );

        Ad createdAd = new Ad();
        createdAd.setPk(1);
        createdAd.setTitle("Test Ad");

        when(adService.createAd(any(CreateOrUpdateAd.class), any(), anyString())).thenReturn(createdAd);

        mockMvc.perform(multipart("/ads")
                        .file(imageFile)
                        .file(propertiesFile))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "owner@example.com")
    @DisplayName("DELETE /ads/{id} - удаление объявления владельцем")
    void deleteAd_ByOwner_ReturnsNoContent() throws Exception {
        doNothing().when(adService).deleteAd(eq(1), anyString());

        mockMvc.perform(delete("/ads/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "owner@example.com")
    @DisplayName("PATCH /ads/{id} - обновление объявления владельцем")
    void updateAd_ByOwner_ReturnsOk() throws Exception {
        CreateOrUpdateAd updateRequest = new CreateOrUpdateAd();
        updateRequest.setTitle("Updated Title");
        updateRequest.setPrice(2000);
        updateRequest.setDescription("Updated Description");

        Ad updatedAd = new Ad();
        updatedAd.setPk(1);
        updatedAd.setTitle("Updated Title");

        when(adService.updateAd(eq(1), any(CreateOrUpdateAd.class), anyString())).thenReturn(updatedAd);

        mockMvc.perform(patch("/ads/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user@example.com")
    @DisplayName("GET /ads/me - получение объявлений авторизованного пользователя")
    void getMyAds_ReturnsOk() throws Exception {
        Ads myAds = Ads.builder()
                .count(0)
                .results(Collections.emptyList())
                .build();

        when(adService.getMyAds(anyString())).thenReturn(myAds);

        mockMvc.perform(get("/ads/me"))
                .andExpect(status().isOk());
    }
}
package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.projection.CreateOrUpdateAd;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;

import java.util.stream.Stream;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static testdata.TestData.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = {"USER", "ADMIN"})
@Transactional
public class AdsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AdRepository adRepository;
    @Autowired
    private UserRepository userRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void shouldCreateAd_correctProperties_correctImage_thenAdCreated() throws Exception {
        MockMultipartFile mockProperties = new MockMultipartFile("properties",
                "CreateOrUpdateJson",
                MediaType.APPLICATION_JSON_VALUE,
                objectMapper.writeValueAsString(CORRECT_CREATE_OR_UPDATE_AD_TEST).getBytes());
        MockMultipartFile mockImage = new MockMultipartFile("image",
                "image.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                "image data".getBytes());

        mockMvc.perform(multipart("/ads")
                        .file(mockProperties)
                        .file(mockImage)
                        .with(user("UserTest@mail.ru"))
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @MethodSource("testdata.TestData#provideIncorrectParamsForCreateAdTestMethod")
    public void shouldCreateAd_IncorrectProperties_correctImage_thenAdCreated(CreateOrUpdateAd createOrUpdateAd) throws Exception {
        MockMultipartFile mockProperties = new MockMultipartFile("properties",
                "CreateOrUpdateJson",
                MediaType.APPLICATION_JSON_VALUE,
                objectMapper.writeValueAsString(createOrUpdateAd).getBytes());
        MockMultipartFile mockImage = new MockMultipartFile("image",
                "image.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                "image data".getBytes());

        mockMvc.perform(multipart("/ads")
                        .file(mockProperties)
                        .file(mockImage)
                        .with(user("UserTest@mail.ru"))
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().is4xxClientError());
    }
    @Test
    void shouldReturnAds_thenDeleteAllAdFromDataBase_ThenReturnAdsWithEmptyList() throws Exception{
        adRepository.deleteAll();
        mockMvc.perform(get("/ads")
                        .with(user("UserTest@mail.ru")))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.count").value(0))
                .andExpect(jsonPath("$.results").isArray())
                .andExpect(jsonPath("$.results").isEmpty());
    }
    @Test
    void shouldReturnCorrectAds() throws Exception{
        mockMvc.perform(get("/ads")
                        .with(user("UserTest@mail.ru")))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.count").value(1))
                .andExpect(jsonPath("$.results").isArray())
                .andExpect(jsonPath("$.results.length()").value(1));
    }
}


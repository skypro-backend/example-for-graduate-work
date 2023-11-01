package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.skypro.homework.dto.ads.CreateOrUpdateAd;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.TestService;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AdvertisementControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AdRepository adRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private TestService testService;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @AfterEach
    public void clearDB() {
        adRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("Получение всех объявлений")
    @WithMockUser
    void shouldReturnAdsCollectionWhenCalled() throws Exception {
        AdEntity adEntity = testService.createTestAd();
        mockMvc.perform(MockMvcRequestBuilders.get("/ads"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.count").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.results[0].author").value(adEntity.getUserEntity().getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.results[0].image").value("/ads/image/" + adEntity.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.results[0].price").value(55555))
                .andExpect(MockMvcResultMatchers.jsonPath("$.results[0].title").value("testTitle"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Добавление объявления")
    @WithMockUser(value = "testEmail@gmail.com")
    void shouldReturnAdWhenCreateAdCalled() throws Exception {
        UserEntity userEntity = testService.createTestUser();
        CreateOrUpdateAd createOrUpdateAd = new CreateOrUpdateAd("createdTitle", 44444, "createdDescription");
        MockMultipartFile request = new MockMultipartFile(
                "properties",
                "properties",
                MediaType.APPLICATION_JSON_VALUE,
                objectMapper.writeValueAsString(createOrUpdateAd).getBytes()
        );
        MockMultipartFile image = new MockMultipartFile(
                "image",
                "image",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                "image".getBytes()
        );
        mockMvc.perform(multipart("/ads")
                .file(request)
                .file(image))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value(userEntity.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(44444))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("createdTitle"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Получение информации об объявлении")
    @WithMockUser
    void shouldReturnExtendedAdWhenCalled() throws Exception {
        AdEntity adEntity = testService.createTestAd();
        mockMvc.perform(get("/ads/{id}", adEntity.getId())
                .content(objectMapper.writeValueAsString(adEntity))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.authorFirstName").value("testFirstName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.authorLastName").value("testLastName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("testDescription"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("testEmail@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.image").value("/ads/image/" + adEntity.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("+77777777777"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(55555))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("testTitle"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Удаление объявления")
    @WithMockUser(value = "testEmail@gmail.com")
    void shouldReturnOkWhenDeleteAdCalled() throws Exception {
        AdEntity adEntity = testService.createTestAd();
        mockMvc.perform(MockMvcRequestBuilders.delete("/ads/{id}", adEntity.getId()))
                .andExpect(status().isOk());
        Assertions.assertFalse(adRepository.findById(adEntity.getId()).isPresent());
    }

    @Test
    @DisplayName("Обновление информации об объявлении")
    @WithMockUser(value = "testEmail@gmail.com")
    void shouldReturnUpdatedInfoAboutAdWhenCalled() throws Exception {
        AdEntity adEntity = testService.createTestAd();
        CreateOrUpdateAd createOrUpdateAd = new CreateOrUpdateAd("updatedTitle", 77777, "updatedDescription");
        mockMvc.perform(MockMvcRequestBuilders.patch("/ads/{id}", adEntity.getId())
                .content(objectMapper.writeValueAsString(createOrUpdateAd))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value(adEntity.getUserEntity().getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.image").value("/ads/image/" + adEntity.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(77777))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("updatedTitle"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Получение объявления авторизованного пользователя")
    @WithMockUser(value = "testEmail@gmail.com")
    void shouldReturnMyAdsCollectionWhenCalled() throws Exception {
        AdEntity adEntity = testService.createTestAd();
        mockMvc.perform(MockMvcRequestBuilders.get("/ads/me"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.count").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.results[0].author").value(adEntity.getUserEntity().getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.results[0].image").value("/ads/image/" + adEntity.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.results[0].price").value(55555))
                .andExpect(MockMvcResultMatchers.jsonPath("$.results[0].title").value("testTitle"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Обновление картинки объявления")
    @WithMockUser(value = "testEmail@gmail.com")
    void shouldReturnOkWhenUpdateAdImageCalled() throws Exception {
        AdEntity adEntity = testService.createTestAd();
        MockMultipartFile image = new MockMultipartFile(
                "image", "image", MediaType.MULTIPART_FORM_DATA_VALUE, "image".getBytes()
        );
        mockMvc.perform(multipart(HttpMethod.PATCH, "/ads/{id}/image", adEntity.getId())
                .file(image))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
        Assertions.assertTrue(adRepository.findById(adEntity.getId()).isPresent());
    }

    @Test
    @DisplayName("Получение картинки объявления")
    @WithMockUser
    void shouldReturnImageByteArrayWhenCalled() throws Exception {
        AdEntity adEntity = testService.createTestAd();
        mockMvc.perform(get("/ads/image/{adId}", adEntity.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("/ads/image/" + adEntity.getId())) // not sure
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Поиск объявления по названию")
    @WithMockUser
    void shouldReturnAdCollectionWhenKeywordCalled() throws Exception {
        AdEntity adEntity = testService.createTestAd();
        mockMvc.perform(MockMvcRequestBuilders.get("/ads/find/{title}", adEntity.getTitle())
                        .contentType(objectMapper.writeValueAsString(adEntity.getTitle()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.count").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.results[0].author").value(adEntity.getUserEntity().getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.results[0].image").value("/ads/image/" + adEntity.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.results[0].price").value(55555))
                .andExpect(MockMvcResultMatchers.jsonPath("$.results[0].title").value("testTitle"))
                .andExpect(status().isOk());
    }

}

package ru.skypro.homework;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.controller.AdController;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.*;
import ru.skypro.homework.entity.AdEntity;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = WebTestConfiguration.class)
@AutoConfigureMockMvc
public class AdControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AdRepository adRepository;
    @MockBean
    private UserRepository userRepository;
    @Autowired
    private AdServiceImpl adService;
    @MockBean
    private ImageServiceImpl imageService;
    @MockBean
    private AdMapper adMapper;
    @InjectMocks
    private AdController adController;
    private UserEntity userEntity;
    private ImageEntity imageEntity;
    private AdEntity adEntity;
    private Ad ad;
    private AdEntity adEntityUpdated;
    private Ad adUpdated;
    @BeforeEach
    void init() {

        imageEntity = new ImageEntity(1, "path");
        userEntity = new UserEntity(1,"user@gmail.com","name","lname",null,"user",Role.USER,null,null,null);

        adEntity = new AdEntity(1,0,"title","desc",null,null,null);
        ad = new Ad(1, null, 1, 0, "title");

        adEntityUpdated = new AdEntity(1,100,"title1","desc1", userEntity, imageEntity, null);
        adUpdated = new Ad(1, "/image/1", 1, 100, "title1");
    }

    @Test
    @Transactional
    public void getAllAdsTest() throws Exception {
        when(adRepository.findAll()).thenReturn(List.of(adEntity));
        when(adMapper.adToAdDTO(adEntity)).thenReturn(ad);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ads")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(1));
    }

    @Test
    @Transactional
    @WithUserDetails(value = "user@gmail.com")
    public void getAdsByCurrentUserTest() throws Exception {
        when(adRepository.findAdsByEmail(any(String.class))).thenReturn(List.of(adEntity));
        when(adMapper.adToAdDTO(adEntity)).thenReturn(ad);


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ads/me")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(1));
    }

    @Test
    @Transactional
    @WithUserDetails(value = "user@gmail.com")
    public void addAdTest() throws Exception {
        AdEntity adEntityCreateOrUpdate = new AdEntity(1, 100, "title1", "desc1", null, null, null);

        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.of(userEntity));
        when(adMapper.createOrUpdateAdDTOToAd(any(CreateOrUpdateAd.class))).thenReturn(adEntityCreateOrUpdate);
        when(imageService.uploadImage(any(MultipartFile.class))).thenReturn(imageEntity);
        when(adRepository.save(any(AdEntity.class))).thenReturn(adEntityUpdated);
        when(adMapper.adToAdDTO(any(AdEntity.class))).thenReturn(adUpdated);

        MockMultipartFile image = new MockMultipartFile("image",null,null, "content".getBytes());
        MockMultipartFile createOrUpdateAdJson = new MockMultipartFile("properties",
                null, "application/json", asJsonString(new CreateOrUpdateAd("title1",100,"desc1")).getBytes());
        mockMvc.perform(MockMvcRequestBuilders
                        .multipart("/ads")
                        .file(image)
                        .file(createOrUpdateAdJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.author").value(adUpdated.getAuthor()))
                .andExpect(jsonPath("$.image").value(adUpdated.getImage()))
                .andExpect(jsonPath("$.pk").value(adUpdated.getPk()))
                .andExpect(jsonPath("$.price").value(adUpdated.getPrice()))
                .andExpect(jsonPath("$.title").value(adUpdated.getTitle()));
    }

    @Test
    @Transactional
    @WithUserDetails(value = "user@gmail.com")
    public void getFullAdTest() throws Exception {
        ExtendedAd extendedAd = new ExtendedAd(1,"name","lname","desc1","user@gmail.com",
                "/image/1",null,100,"title1");
        when(adRepository.findById(any(Integer.class))).thenReturn(Optional.of(adEntityUpdated));
        when(adMapper.adEntityToExtendedAdDTO(adEntityUpdated)).thenReturn(extendedAd);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ads/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pk").value(extendedAd.getPk()))
                .andExpect(jsonPath("$.authorFirstName").value(extendedAd.getAuthorFirstName()))
                .andExpect(jsonPath("$.authorLastName").value(extendedAd.getAuthorLastName()))
                .andExpect(jsonPath("$.description").value(extendedAd.getDescription()))
                .andExpect(jsonPath("$.email").value(extendedAd.getEmail()))
                .andExpect(jsonPath("$.image").value(extendedAd.getImage()))
                .andExpect(jsonPath("$.phone").value(extendedAd.getPhone()))
                .andExpect(jsonPath("$.price").value(extendedAd.getPrice()))
                .andExpect(jsonPath("$.title").value(extendedAd.getTitle()));
    }

    @Test
    @Transactional
    @WithUserDetails(value = "user@gmail.com")
    public void updateAdTest() throws Exception {
        AdEntity adEntityWithUser = new AdEntity(1, 10, "title", "desc", userEntity, imageEntity, null);
        when(adRepository.findById(any(Integer.class))).thenReturn(Optional.of(adEntityWithUser));
        when(adRepository.save(any(AdEntity.class))).thenReturn(adEntityUpdated);
        when(adMapper.adToAdDTO(any(AdEntity.class))).thenReturn(adUpdated);

        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/ads/{id}", 1)
                        .content(asJsonString(new CreateOrUpdateAd("title1", 100, "desc1")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author").value(adUpdated.getAuthor()))
                .andExpect(jsonPath("$.image").value(adUpdated.getImage()))
                .andExpect(jsonPath("$.pk").value(adUpdated.getPk()))
                .andExpect(jsonPath("$.price").value(adUpdated.getPrice()))
                .andExpect(jsonPath("$.title").value(adUpdated.getTitle()));
    }

    @Test
    @Transactional
    @WithUserDetails(value = "user@gmail.com")
    public void removeAdTest() throws Exception {
        when(adRepository.findById(any(Integer.class))).thenReturn(Optional.of(adEntityUpdated));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/ads/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    @Transactional
    @WithUserDetails(value = "user@gmail.com")
    public void updateImageTest() throws Exception {
        AdEntity adEntityNoImage = new AdEntity(1,0,"title","desc", userEntity,null,null);

        when(adRepository.findById(any(Integer.class))).thenReturn(Optional.of(adEntityNoImage));
        when(imageService.uploadImage(any(MultipartFile.class))).thenReturn(imageEntity);

        MockPart image = new MockPart("image","filename", "content".getBytes());
        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/ads/{id}/image", 1)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .with(request -> {
                            request.addPart(image);
                            return request;
                        }))
                .andExpect(status().isOk());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

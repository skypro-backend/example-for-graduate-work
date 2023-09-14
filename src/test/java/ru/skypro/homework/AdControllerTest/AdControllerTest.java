//package ru.skypro.homework.AdControllerTest;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import ru.skypro.homework.controller.AdController;
//import ru.skypro.homework.dto.ad.AdDTO;
//import ru.skypro.homework.dto.ad.CreateAdRequest;
//import ru.skypro.homework.dto.ad.UpdateAdRequest;
//import ru.skypro.homework.entity.Ad;
//import ru.skypro.homework.mapper.AdMapper;
//import ru.skypro.homework.service.impl.AdService;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//public class AdControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Mock
//    private AdService adService;
//
//    @Mock
//    private AdMapper adMapper;
//
//    private AdController adController;
//
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        adController = new AdController(adService, adMapper);
//        mockMvc = MockMvcBuilders.standaloneSetup(adController).build();
//        objectMapper = new ObjectMapper();
//    }
//
//    @Test
//    void testGetAllAds() throws Exception {
//        // Создаем список AdDTO для ваших тестов
//        List<AdDTO> adDTOList = Collections.singletonList(new AdDTO());
//
//        // Мокаем сервис, чтобы вернуть список AdDTO при вызове getAllAds()
//        when(adService.getAllAds()).thenReturn(adDTOList);
//
//        mockMvc.perform(get("/ads/"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$[0].title").value(adDTOList.get(0).getTitle()));
//
//        // Проверяем, что метод getAllAds() был вызван один раз
//        verify(adService, times(1)).getAllAds();
//    }
//
//    @Test
//    void testGetAdById() throws Exception {
//        long adId = 1L;
//        AdDTO adDTO = new AdDTO();
//        adDTO.setId(adId);
//        adDTO.setTitle("Sample Ad");
//
//        when(adService.getAdById(adId)).thenReturn(adDTO);
//
//        mockMvc.perform(get("/ads/{adId}", adId))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(adDTO.getId()))
//                .andExpect(jsonPath("$.title").value(adDTO.getTitle()));
//
//        verify(adService, times(1)).getAdById(adId);
//    }
//
//    @Test
//    void testCreateAd() throws Exception {
//        CreateAdRequest createAdRequest = new CreateAdRequest();
//        createAdRequest.setTitle("Sample Ad");
//        createAdRequest.setPrice(100);
//        createAdRequest.setDescription("Description");
//
//        Ad ad = new Ad();
//        ad.setId(1L);
//        ad.setTitle("Sample Ad");
//        ad.setPrice(100);
//        ad.setDescription("Description");
//
//        when(adService.createAd(createAdRequest)).thenReturn(ad);
//
//        mockMvc.perform(post("/ads/")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(createAdRequest)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(ad.getId()))
//                .andExpect(jsonPath("$.title").value(ad.getTitle()));
//
//        verify(adService, times(1)).createAd(createAdRequest);
//    }
//
//    @Test
//    void testUpdateAd() throws Exception {
//        long adId = 1L;
//        UpdateAdRequest updateAdRequest = new UpdateAdRequest();
//        updateAdRequest.setTitle("Updated Ad");
//        updateAdRequest.setPrice(200);
//        updateAdRequest.setDescription("Updated Description");
//
//        AdDTO updatedAdDTO = new AdDTO();
//        updatedAdDTO.setId(adId);
//        updatedAdDTO.setTitle("Updated Ad");
//        updatedAdDTO.setPrice(200);
//        updatedAdDTO.setDescription("Updated Description");
//
//        when(adService.updateAd(adId, updateAdRequest)).thenReturn(updatedAdDTO);
//
//        mockMvc.perform(put("/ads/{adId}", adId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(updateAdRequest)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(updatedAdDTO.getId()))
//                .andExpect(jsonPath("$.title").value(updatedAdDTO.getTitle()));
//
//        verify(adService, times(1)).updateAd(adId, updateAdRequest);
//    }
//
//    @Test
//    void testDeleteAd() throws Exception {
//        long adId = 1L;
//
//        mockMvc.perform(delete("/ads/{adId}", adId))
//                .andExpect(status().isOk());
//
//        verify(adService, times(1)).deleteAd(adId);
//    }
//
//    @Test
//    void testUpdateAdImage() throws Exception {
//        long adId = 1L;
//        byte[] imageBytes = "SampleImageData".getBytes();
//        MockMultipartFile imageFile = new MockMultipartFile("image", "image.jpg", "image/jpeg", imageBytes);
//
//        AdDTO adDTO = new AdDTO();
//        adDTO.setId(adId);
//        adDTO.setTitle("Sample Ad");
//
//        Ad ad = new Ad();
//        ad.setId(adId);
//        ad.setTitle("Sample Ad");
//
//        when(adService.getAdById(adId)).thenReturn(adDTO);
//
//        mockMvc.perform(multipart("/ads/{adId}/image", adId)
//                        .file(imageFile))
//                .andExpect(status().isOk());
//
//        verify(adService, times(1)).updateAdImage(ad);
//    }
//}
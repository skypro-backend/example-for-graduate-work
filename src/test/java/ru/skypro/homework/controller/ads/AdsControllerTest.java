package ru.skypro.homework.controller.ads;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.config.WebMvcConfig;
import ru.skypro.homework.dto.ads.in.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ads.out.AdDto;
import ru.skypro.homework.dto.ads.out.ads.Ad;
import ru.skypro.homework.dto.comments.out.CommentDto;
import ru.skypro.homework.entity.comments.Comment;
import ru.skypro.homework.service.ads.AdsService;
import ru.skypro.homework.service.image.ImageService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = WebMvcConfig.class)
class AdsControllerTest {

    @Mock
    private AdsService adsService;
    @Mock
    private ImageService imageService;

    @InjectMocks
    AdsController adsController;
    private final Ad testAd = new Ad();
    private final Comment testComment = new Comment();
    private final CommentDto commentDto = new CommentDto();
    private final CreateOrUpdateAdDto createAdDto = new CreateOrUpdateAdDto();

    @BeforeEach
    public void init() {
        testAd.setPk(100);
        testAd.setDescription("test description");
        testAd.setTitle("test title");
        testAd.setPrice(1000);

        testComment.setPk(200);

        commentDto.setPk(300);
        commentDto.setText("test text");

        createAdDto.setDescription("New Description");
        createAdDto.setTitle("New Title");
        createAdDto.setPrice(4000);
    }

    @Test
    void addAd() {
        AdDto adDto = new AdDto();
        MultipartFile image = new MockMultipartFile("test.jpg", "test.jpg",
                "image/jpeg", "test image".getBytes());

        when(adsService.addAd(createAdDto, image)).thenReturn(adDto);

        ResponseEntity<AdDto> response = adsController.addAd(createAdDto, image);

        verify(adsService).addAd(createAdDto, image);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(adDto, response.getBody());
        assertNotNull(adDto);
    }
}
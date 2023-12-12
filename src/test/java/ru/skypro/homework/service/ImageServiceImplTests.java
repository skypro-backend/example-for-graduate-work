package ru.skypro.homework.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.impl.ImageServiceImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ImageServiceImplTests {

    @Mock
    private ImageRepository imageRepository;

    @InjectMocks
    private ImageServiceImpl imageService;

    @Test
    public void shouldCorrectlyUploadImage() throws IOException {
        MockMultipartFile multipartFile = new MockMultipartFile("image", "image.png","png", "data".getBytes(StandardCharsets.UTF_8));
        Image image = new Image();
        image.setData(multipartFile.getBytes());
        image.setMediaType(multipartFile.getContentType());

        when(imageRepository.save(image)).thenReturn(image);

        Image expected = image;
        Image actual = imageService.uploadImage(multipartFile);
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldCorrectlyFindImage() {
        Image image = new Image();

        when(imageRepository.findById(1)).thenReturn(Optional.of(image));

        Image expected = image;
        Image actual = imageService.findImage(1);
        Assertions.assertEquals(expected, actual);

    }

}

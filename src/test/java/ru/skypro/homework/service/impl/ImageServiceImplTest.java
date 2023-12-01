package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repository.ImageRepository;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ImageServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ImageServiceImplTest {
    @MockBean
    private ImageRepository imageRepository;

    @Autowired
    private ImageServiceImpl imageServiceImpl;

    /**
     * Method under test:  {@link ImageServiceImpl#getImage(Long)}
     */
    @Test
    void testGetImage() {
        when(imageRepository.findById(Mockito.<Long>any())).thenThrow(new RuntimeException("foo"));
        assertThrows(RuntimeException.class, () -> imageServiceImpl.getImage(1L));
        verify(imageRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link ImageServiceImpl#addImage(MultipartFile)}
     */
    @Test
    void testAddImage() throws IOException {
        Image image = new Image();
        image.setData("AXAXAXAX".getBytes("UTF-8"));
        image.setFileSize(3L);
        image.setId(1L);
        image.setMediaType("Media Type");
        when(imageRepository.save(Mockito.<Image>any())).thenReturn(image);
        Image actualAddImageResult = imageServiceImpl
                .addImage(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
        verify(imageRepository).save(Mockito.<Image>any());
        assertNull(actualAddImageResult.getMediaType());
        assertEquals(8L, actualAddImageResult.getFileSize());
        byte[] expectedData = "AXAXAXAX".getBytes("UTF-8");
        assertArrayEquals(expectedData, actualAddImageResult.getData());
    }

    /**
     * Method under test: {@link ImageServiceImpl#deleteImage(Long)}
     */
    @Test
    void testDeleteImage() {
        doNothing().when(imageRepository).deleteById(Mockito.<Long>any());
        imageServiceImpl.deleteImage(1L);
        verify(imageRepository).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link ImageServiceImpl#deleteImage(Long)}
     */
    @Test
    void testDeleteImage2() {
        doThrow(new RuntimeException("foo")).when(imageRepository).deleteById(Mockito.<Long>any());
        assertThrows(RuntimeException.class, () -> imageServiceImpl.deleteImage(1L));
        verify(imageRepository).deleteById(Mockito.<Long>any());
    }
}

package ru.skypro.homework.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.impl.ImageServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageServiceImplTest {
    @Mock
    private ImageRepository imageRepository;
    @InjectMocks
    private ImageServiceImpl out;


    Image image = new Image("jpeg", new byte[1]);

    @Test
    void uploadImage() {

    }

    @Test
    void getImage() {
        when(imageRepository.getReferenceById(image.getId())).thenReturn(image);
        assertEquals(out.getImage(image.getId()), image.getImage());
        verify(imageRepository, times(1)).getReferenceById(image.getId());
    }

    @Test
    void deleteImage() {
        out.deleteImage(image.getId());
        verify(imageRepository, times(1)).deleteById(image.getId());
    }
}
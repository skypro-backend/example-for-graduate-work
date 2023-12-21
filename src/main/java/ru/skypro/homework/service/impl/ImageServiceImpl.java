package ru.skypro.homework.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.service.ImageService;

public class ImageServiceImpl implements ImageService {
    @Override
    public ResponseEntity<byte[]> getImage(Long id) {
        return null;
    }

    @Override
    public Image addImage(MultipartFile image) {
        return null;
    }

    @Override
    public void deleteImage(Integer imageId) {

    }
}

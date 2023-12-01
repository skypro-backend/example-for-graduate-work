package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;

public interface ImageService {
    ResponseEntity<byte[]> getImage(Long id);

    Image addImage(MultipartFile image);

    void deleteImage(Long imageId);
}

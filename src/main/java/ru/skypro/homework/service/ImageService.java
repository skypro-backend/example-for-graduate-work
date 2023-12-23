package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;

public interface ImageService {
    ResponseEntity<byte[]> getImage(Integer id);

    Image addImage(MultipartFile image);

    void deleteImage(Integer imageId);
}

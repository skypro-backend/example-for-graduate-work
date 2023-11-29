package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.PhotoAd;

import java.io.IOException;

public interface PhotoAdService {
    ResponseEntity<byte[]> getImage(Long id) throws IOException;

    PhotoAd addPhoto (String path, MultipartFile image);
}

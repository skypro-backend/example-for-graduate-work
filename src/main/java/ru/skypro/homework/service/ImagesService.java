package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Images;

import java.io.IOException;

public interface ImagesService {
    ResponseEntity<byte[]> getImage(Long id) throws IOException;

    Images addPhoto (String path, MultipartFile image);
}

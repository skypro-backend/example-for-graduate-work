package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository repository;

    @Override
    public ResponseEntity<?> getImage(int id) {
        ImageEntity image = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Image with id " + id + " not fond"));
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .contentType(MediaType.IMAGE_PNG)
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }

//    @Override
//    public String updateImage(int id, MultipartFile file, Authentication authentication) {
//        return null;
//    }
//
//    @Override
//    public String updateImage(MultipartFile file, Authentication authentication) {
//        return null;
//    }
}

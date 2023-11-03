package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skypro.homework.exceptions.ImageNotFoundException;
import ru.skypro.homework.model.ImageModel;
import ru.skypro.homework.repository.ImageRepo;
import ru.skypro.homework.service.ImageService;

import java.io.ByteArrayInputStream;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    ImageRepo imageRepo;

    public ResponseEntity<?> getImage(String id) {
        ImageModel image = imageRepo.findById(id).orElseThrow(ImageNotFoundException::new);
        assert image != null;
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }

}

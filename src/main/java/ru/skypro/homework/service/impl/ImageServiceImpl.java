package ru.skypro.homework.service.impl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.exception.ImageNotFoundException;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;

@Slf4j
@Service
@Data
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    @Override
    public ResponseEntity<byte[]> getImage(Long id) {
        Image image = imageRepository.findById(id).orElseThrow(ImageNotFoundException::new);
        byte[] imageBytes = image.getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(image.getMediaType()));
        headers.setContentLength(imageBytes.length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(imageBytes);
    }

    @Override
    public Image addImage(MultipartFile image) {
        Image imageNew = new Image();
        try {
            imageNew.setData(image.getBytes());
            imageNew.setMediaType(image.getContentType());
            imageNew.setFileSize(image.getSize());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        imageRepository.save(imageNew);
        return imageNew;
    }

    @Override
    public void deleteImage(Long imageId) {
        imageRepository.deleteById(imageId);
    }
}

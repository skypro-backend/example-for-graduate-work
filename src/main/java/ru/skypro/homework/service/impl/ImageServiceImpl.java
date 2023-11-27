package ru.skypro.homework.service.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skypro.homework.exception.PhotoAdNotFoundException;
import ru.skypro.homework.model.PhotoAd;
import ru.skypro.homework.repository.PhotoAdRepository;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageServiceImpl implements ImageService {
    private final PhotoAdRepository photoAdRepository;

    public ImageServiceImpl(PhotoAdRepository photoAdRepository) {
        this.photoAdRepository = photoAdRepository;
    }

    @Override
    public ResponseEntity<byte[]> getImage(Long id)throws IOException {
        PhotoAd photoAd = null;

            photoAd = photoAdRepository.findById(id).orElseThrow(PhotoAdNotFoundException::new);
        byte[] imageBytes = readImageBytes(photoAd.getFilePath());
        HttpHeaders headers = new HttpHeaders();
        assert photoAd != null;
        headers.setContentType(MediaType.parseMediaType(photoAd.getMediaType()));
        headers.setContentLength(imageBytes.length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(imageBytes);
    }
    private static byte[] readImageBytes(String imagePath) throws IOException {
        Path path = Paths.get(imagePath);
        return Files.readAllBytes(path);
    }
}

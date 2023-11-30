package ru.skypro.homework.service.impl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.exception.PhotoAdNotFoundException;
import ru.skypro.homework.model.Images;
import ru.skypro.homework.repository.ImagesRepository;
import ru.skypro.homework.service.ImagesService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Slf4j
@Service
@Data
public class ImagesServiceImpl implements ImagesService {
    private final ImagesRepository imagesRepository;
    @Value("${path.to.images.folder}")
    private String photoAvatar;
    @Override
    public ResponseEntity<byte[]> getImage(Long id) throws IOException {
        Images images = imagesRepository.findById(id).orElseThrow(PhotoAdNotFoundException::new);
        byte[] imageBytes = images.getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(images.getMediaType()));
        headers.setContentLength(imageBytes.length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(imageBytes);
    }
    @Override
    public Images addPhoto (String path, MultipartFile image){
        Path filePath = Path.of(photoAvatar, path + "." + getExtension(Objects.requireNonNull(image.getOriginalFilename())));
        Images photoAd;
        try {
            photoAd = new Images();
            uploadPhotoAdd(filePath,image);
            photoAd.setFilePath(filePath.toString());
            photoAd.setFileSize(image.getSize());
            photoAd.setMediaType(image.getContentType());
            photoAd.setData(image.getBytes());
            photoAd = imagesRepository.save(photoAd);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return  photoAd;
    }
    public void uploadPhotoAdd(Path filePath, MultipartFile image) throws IOException {
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (InputStream is = image.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }

    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);

    }
}

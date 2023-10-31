package ru.skypro.homework.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;

public interface ImageService {

    Image updateImage(MultipartFile file, Image image);

    Resource getImageFromFile(String imageName);
}
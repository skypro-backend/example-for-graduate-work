package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;

public interface ImageService {
    Image saveImage(MultipartFile imageFile);

    Image getImageById(Long id);
}

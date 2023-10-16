package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.entities.ImageEntity;

public interface ImageService {
    ImageEntity downloadImage(MultipartFile image);
}

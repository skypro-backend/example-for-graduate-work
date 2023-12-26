package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;

public interface ImageService {

    Image uploadImage(MultipartFile imageFile);

    void removeImage(Image image);

    Image getImage(Long id);
}

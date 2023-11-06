package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.models.Image;

public interface ImageService {

    void upload(String imagePath, MultipartFile file);

    void deleteFromDisk(String imagePath);

    void update(Image image, MultipartFile file);

    byte[] download(String imagePath);

    Image save(Image image);

    String createImagePath(MultipartFile file);

}

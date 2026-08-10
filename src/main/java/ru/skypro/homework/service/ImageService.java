package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String saveImage(MultipartFile file, String subDirectory, String fileName);
    byte[] getImage(String filePath);
    void deleteImage(String filePath);
    String getImageUrl(String filePath);
}

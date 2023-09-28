package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String uploadImage(MultipartFile image);
    byte[] getImage(String id);
    void deleteImage(String id);
}

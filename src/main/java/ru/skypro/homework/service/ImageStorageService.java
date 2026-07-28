package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageService {

    String save(MultipartFile file, String fileNamePrefix);

    byte[] read(String imageUrl);

    String detectContentType(String imageUrl);
}

package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageService {

    String save(MultipartFile image);

    StoredImage read(String fileName);
}

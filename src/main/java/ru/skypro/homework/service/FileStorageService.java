package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String saveUserImage(Integer id, MultipartFile image);

    String saveAdImage(Integer id, MultipartFile image);

    byte[] getImage(String path);
}

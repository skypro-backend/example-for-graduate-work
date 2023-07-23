package ru.skypro.homework.service;

import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    void updateUserImage(String username, MultipartFile file);

    void updateAdImage(Integer id, MultipartFile file);

    FileSystemResource getUserImage(Integer id) throws IOException;

    FileSystemResource getAdImage(Integer id) throws IOException;
}

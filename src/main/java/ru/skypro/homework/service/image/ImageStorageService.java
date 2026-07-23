package ru.skypro.homework.service.image;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageStorageService {

    String saveUserImage(MultipartFile image) throws IOException;

    String saveAdImage(MultipartFile image) throws IOException;

    byte[] loadImage(String fileName) throws IOException;
}
package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;

public interface FileService {
    void downloadImage(HttpServletResponse response, String imagePath) throws IOException;
    void uploadImage(MultipartFile image, Path filePath) throws IOException;
}

package ru.skypro.homework.service.image;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

public interface ImageService {
    String createImage(MultipartFile file) throws MalformedURLException;
    void deleteImage(String url) throws IOException;
    byte[] getImage(String imageName);
}
package ru.skypro.homework.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String saveImage(MultipartFile image);

    void deleteImage(String filename);

    Resource getImage(String filename);

}
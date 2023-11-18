package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String uploadImage(MultipartFile file);


    byte[] getImage(long id);
}

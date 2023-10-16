package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    void uploadUserImage(MultipartFile file) throws IOException;

    byte[] uploadAdImage(Integer adPk, MultipartFile file) throws IOException;

}

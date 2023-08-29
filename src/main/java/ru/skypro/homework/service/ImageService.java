package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService<T> {
    T uploadImage(MultipartFile file) throws IOException;

    T getImageById(Integer id);
    void remove(T object);
}

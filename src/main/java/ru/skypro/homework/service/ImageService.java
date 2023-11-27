package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface ImageService {
    ResponseEntity<byte[]> getImage(Long id) throws IOException;
}

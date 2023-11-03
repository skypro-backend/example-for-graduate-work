package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;

public interface ImageService {
    ResponseEntity<?> getImage(String id);
}

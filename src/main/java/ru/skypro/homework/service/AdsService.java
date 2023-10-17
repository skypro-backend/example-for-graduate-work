package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface AdsService {
    ResponseEntity<?> updateImage(Integer idPk, MultipartFile image);
}

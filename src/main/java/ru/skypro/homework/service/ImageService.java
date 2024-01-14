package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    ResponseEntity<?> getImage(int id);

//    String updateImage(int id, MultipartFile file, Authentication authentication);
//
//    String updateImage(MultipartFile file, Authentication authentication);
}

package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String updateAdsImage(Integer id, MultipartFile image);
}

package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void uploadUserImage(MultipartFile file);

    byte[] uploadAdsImage(long adsId, MultipartFile file);

    byte[] getImage(long id);
}

package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;


public interface ImageService {

    byte[] getImage(Integer id);
    ImageEntity uploadImage(MultipartFile file);
    void deleteImage(AdEntity adEntity);
    void deleteImage(UserEntity userEntity);
}



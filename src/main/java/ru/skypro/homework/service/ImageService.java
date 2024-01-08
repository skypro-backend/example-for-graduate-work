package ru.skypro.homework.service;


import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.ImageEntity;

import javax.transaction.Transactional;

public interface ImageService {
    @Transactional
    byte[] getImage(Integer id) ;

    ImageEntity uploadImageToServer(MultipartFile adImage, Integer firstPartOfImageName, String secondPartOfImageName);
}

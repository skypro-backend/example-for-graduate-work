package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.ImageEntity;

import java.io.IOException;

public interface ImageService {

    ImageEntity uploadImage(MultipartFile imageFile) throws IOException;


    ImageEntity getImageById(long id);


    void remove(ImageEntity image);

    //Далее до конца
    // методы для загрузки аватарок для пользователя
    void updateUserImage(MultipartFile image, Authentication authentication)throws IOException;

    ImageEntity findUserAvatar(String id);

    byte[] getImage(String imagePath);
}

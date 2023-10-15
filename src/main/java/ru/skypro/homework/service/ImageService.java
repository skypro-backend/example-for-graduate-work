package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.ImageEntity;

import java.io.IOException;

public interface ImageService {

    void uploadUserImage(MultipartFile file) throws IOException;

    byte[] uploadAdImage(Integer adPk, MultipartFile file) throws IOException;

    ImageEntity findUserImage(Integer userId);

    ImageEntity findAdImage(Integer adPk);

}

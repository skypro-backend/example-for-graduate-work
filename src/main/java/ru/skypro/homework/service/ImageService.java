package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.ImageEntity;

import java.io.IOException;

public interface ImageService {

    byte[] getImage(Integer id) throws IOException;

    ImageEntity uploadImage(MultipartFile file);

}



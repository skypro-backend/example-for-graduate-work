package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.PhotoEntity;

import java.io.IOException;

public interface PhotoService {
    PhotoEntity saveImage(MultipartFile imageFile) throws IOException;
    PhotoEntity getImage(Integer imageId);
    PhotoEntity updateImage(MultipartFile image, Integer imageId) throws IOException;
    byte[] getByteFromFile(String path) throws IOException;
}

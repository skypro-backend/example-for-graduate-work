package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;

import java.io.IOException;

public interface ImageService {
    Image saveImageToUser(MultipartFile imageFile) throws IOException;

    Image updateImage(MultipartFile imageFile, Ad id) throws IOException;

    Image saveImageToDb(Image image);

    byte[] getImage(int imageId) throws IOException;

    boolean checkUserAvatar(int userId);
}

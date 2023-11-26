package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;

import java.io.IOException;

public interface ImageService {

    void uploadImage(User user, MultipartFile imageFile) throws IOException;
    void uploadImage(Ad ad, MultipartFile imageFile) throws IOException;
    Image findImage(long id);
}

package ru.skypro.homework.service.image;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    String consumeImageOfGoods(MultipartFile image) throws IOException;

    String consumeImageOfAvatar(MultipartFile image) throws IOException;

    void deleteImageOfGoods(String pathToImage) throws IOException;

    void deleteImageOfAvatars(String urlToImage) throws IOException;
}

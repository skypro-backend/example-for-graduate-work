package ru.skypro.homework.service.image;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface ImageService {
    String consumeImageOfGoods(MultipartFile image) throws IOException;

    String consumeImageOfAvatar(MultipartFile image) throws IOException;

    void deleteImageOfGoods(String urlToImage) throws IOException;

    void deleteImageOfAvatars(String urlToImage) throws IOException;

    Path getFullPathToImageOfGoods(String urlToImage);

    Path getFullPathToImageOfAvatars(String urlToImage);

    byte[] imageToByteArray(Path pathToImage) throws IOException;
}

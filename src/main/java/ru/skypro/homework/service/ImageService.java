package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.pojo.Image;

import java.io.IOException;

public interface ImageService {

    String saveFile(byte[] data, String fileName) throws IOException;

    Image uploadAvatar(MultipartFile file, Authentication authentication) throws IOException;

    Image uploadImage(MultipartFile file, Long pk) throws IOException;

    Image saveImage(Image image);

}

package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;

import java.io.IOException;

public interface ImageService {

    byte[] getImageInByte(String fileName);
    Image changeUserImage(Long userId, Long imageId, MultipartFile multipartFile) throws IOException;
    Image changeAdImage(Long idAd, Long imageId, MultipartFile multipartFile) throws IOException;


}

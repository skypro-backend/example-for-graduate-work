package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;

public interface ImageService {

    Image createImage(MultipartFile image);


    Image updateImage(MultipartFile newImage, Image oldImage);

    byte[] getImage(Integer id);
}

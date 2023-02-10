package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.dto.AdsDto;

public interface ImageService {
    String saveImage(MultipartFile image, AdsDto adsDto);

    byte [] getImage(Integer id);
}

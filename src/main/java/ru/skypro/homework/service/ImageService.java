package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.models.entity.Images;

public interface ImageService {

    Images addImage(MultipartFile file);

    Images getImage(Integer id);

    void removeImage(Integer id);

    Images updateImage(Integer id, MultipartFile file);
}

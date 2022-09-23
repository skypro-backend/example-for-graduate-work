package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.models.entity.Images;

import java.awt.*;
import java.io.IOException;

public interface ImageService {

    Images addImage(MultipartFile file) throws IOException;

    Images findImage(Integer id);

    void removeImage(Integer id);

}

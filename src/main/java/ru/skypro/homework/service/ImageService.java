package ru.skypro.homework.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.ImageRepository;

import java.io.IOException;

public interface ImageService {

      Image uploadImage(MultipartFile imageFile) throws IOException;


    Image findImage(Integer id);

}

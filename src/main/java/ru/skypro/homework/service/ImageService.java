package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.exceptions.ImageSizeExceededException;
import ru.skypro.homework.model.Image;

import javax.transaction.Transactional;
import java.io.IOException;

public interface ImageService {


    Image findImageById(int id);

    @Transactional
    Image upLoadImage(MultipartFile file) throws IOException, ImageSizeExceededException;

    void deleteImage(int imageId);

    void refactorImage(Long id, MultipartFile image);
    boolean checkUserImage(Long userId);
}

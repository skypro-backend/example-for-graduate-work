package ru.skypro.kakavito.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.kakavito.exceptions.ImageSizeExceededException;
import ru.skypro.kakavito.model.Image;

import javax.transaction.Transactional;
import java.io.IOException;

public interface ImageService {


    Image findImageById(int id);

    @Transactional
    Image upLoadImage(MultipartFile file) throws IOException, ImageSizeExceededException;

    void deleteImage(int imageId);

    void refactorImage(int id, MultipartFile image);
    boolean checkUserImage(int userId);
}

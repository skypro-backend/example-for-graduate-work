package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);
    private final ImageRepository imageRepository;
    private final File defaultAvatar = new File("images/default_avatar.png");

    /**
     * Loading a default avatar
     * <br>
     * Using the repository method to check for null {@link JpaRepository#findAll()}
     * <br>
     * Using the repository method to save avatar in DB {@link JpaRepository#save(Object)}
     * <br>
     * Using setter for setting media type of image {@link Image#setMediaType(String)}
     * <br>
     * Using setter for setting image to DB {@link Image#setData(byte[])}
     */
    @PostConstruct
    private void uploadDefaultAvatar() throws IOException{
        if(imageRepository.findAll().isEmpty()) {
            logger.info("Загружаем аватар по-умолчанию");
            Image image = new Image();
            image.setMediaType(Files.probeContentType(defaultAvatar.toPath()));
            image.setData(Files.readAllBytes(defaultAvatar.toPath()));
            imageRepository.save(image);
        }
    }

    /**
     * Loading image
     *<br>
     * Using the repository method to save avatar in DB {@link JpaRepository#save(Object)}
     * <br>
     * Using setter for setting media type of image {@link Image#setMediaType(String)}
     *  <br>
     * Using setter for setting image to DB {@link Image#setData(byte[])}
     */
    @Override
    public Image uploadImage(MultipartFile imageFile) throws IOException {
        logger.info("Загружаем изображение");
        Image image = new Image();
        image.setMediaType(imageFile.getContentType());
        image.setData(imageFile.getBytes());
        imageRepository.save(image);
        return image;
    }

    /**
     * Searching image by id in DB
     * Using repository method {@link ImageRepository#findById(Object)}
     * @param id
     * @return
     */
    @Override
    public Image findImage(Integer id) {
        logger.info("Ищем изображение по его id - " + id);
        return imageRepository.findById(id).get();
    }

}

package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repository.ImageRepository;
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
     * Uploading the default avatar
     * <br>
     * Using {@link JpaRepository#findAll()}, {@link JpaRepository#save(Object)}, {@link Image#setMediaType(String)}, {@link Image#setData(byte[])}
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
     * Uploading image
     *<br>
     * Using {@link Image#setMediaType(String)}, {@link Image#setData(byte[])}, {@link JpaRepository#save(Object)}
     * @param imageFile
     * @return Image
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
     * using {@link ImageRepository#findById(Object)}
     * @param id
     * @return Image
     */
    @Override
    public Image findImage(Integer id) {
        logger.info("Ищем изображение по его id - " + id);
        return imageRepository.findById(id).get();
    }

}

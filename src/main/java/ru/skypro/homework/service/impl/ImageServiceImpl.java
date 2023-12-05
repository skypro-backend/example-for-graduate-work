package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

    @PostConstruct
    private void uploadDefaultAvatar() throws IOException{
        if(imageRepository.findAll().isEmpty()) {
            Image image = new Image();
            image.setMediaType(Files.probeContentType(defaultAvatar.toPath()));
            image.setData(Files.readAllBytes(defaultAvatar.toPath()));
            imageRepository.save(image);
        }
    }

    @Override
    public Image uploadImage(MultipartFile imageFile) throws IOException {
        Image image = new Image();
        image.setMediaType(imageFile.getContentType());
        image.setData(imageFile.getBytes());
        imageRepository.save(image);
        return image;
    }

    @Override
    public Image findImage(Integer id) {
        logger.info("Ищем изображение по его id - {}");
        return imageRepository.findById(id).get();
    }

}

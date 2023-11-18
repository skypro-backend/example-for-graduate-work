package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;

import java.io.*;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Value("${path.to.image.folder}")
    private String imageDir;

    private final ImageRepository imageRepository;
    @Override
    public String uploadImage(MultipartFile file) {
        Image image = new Image();
        image.setId(Long.parseLong(UUID.randomUUID().toString())); // генерируем уникальный идентификатор
        try {
            byte[] bytes = file.getBytes();
            image.setImage(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageRepository.save(image);
        return String.valueOf(image.getId());
    }


    @Override
    public byte[] getImage(long id) {
        Image image = imageRepository.findById(id).orElse(null);

        return image.getImage();
    }

}

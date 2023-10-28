package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.repository.ImagesRepository;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImagesRepository imagesRepository;

    @Override
    public byte[] getImageInByteById(int id) {
        return imagesRepository.findById(id).orElseThrow().getImage();
    }

    @Override
    public Image updateImage(MultipartFile file, Image image) {
        if (image == null) {
            image = new Image();
        }
        try {
            byte[] bytes = file.getBytes();
            image.setImage(bytes);
        } catch (IOException e) {
            log.error("Failed to transform incoming image to bytes", e);
        }
        return image;
    }
}

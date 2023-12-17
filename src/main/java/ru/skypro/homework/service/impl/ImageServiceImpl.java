package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public Image createImage(MultipartFile image) {
        Image newImage = new Image();
        try {
            byte[] bytes = image.getBytes();
            newImage.setBytes(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        newImage.setId(Integer.valueOf(UUID.randomUUID().toString()));
        return imageRepository.save(newImage);
    }

    @Override
    public Image updateImage(MultipartFile newImage, Image oldImage) {
        try {
            byte[] bytes = newImage.getBytes();
            oldImage.setBytes(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return imageRepository.save(oldImage);
    }

    @Override
    public byte[] getImage(Integer id) {
        Image image = imageRepository.findById(id).orElseThrow();
        return image.getBytes();
    }
}

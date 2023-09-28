package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public String uploadImage(MultipartFile image) {
        Image entity = new Image();
        String name = UUID.randomUUID()+type(image);
        entity.setId(name);
        try {
            byte[] bytes = image.getBytes();
            entity.setImage(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        entity = imageRepository.save(entity);
        return entity.getId();

    }
    @Override
    public byte[] getImage(String id) {
        Image entity = new Image();
        entity = imageRepository.getReferenceById(id);
        return entity.getImage();
    }

    @Override
    public void deleteImage(String id) {
        imageRepository.deleteById(id);
    }
    private String type(MultipartFile image) {

        String type = image.getContentType();
        assert type != null;
        type = type.replace("image/", ".");

        return type;
    }
}

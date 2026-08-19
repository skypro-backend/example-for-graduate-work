package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repository.ImageRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.UUID;

@Service
@Transactional
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public String uploadImage(MultipartFile file) throws IOException {
        Image image = new Image();
        String id = UUID.randomUUID().toString();
        image.setId(id);
        image.setData(file.getBytes());
        imageRepository.save(image);
        return "/images/" + id; // Возвращаем путь, который поймет фронтенд
    }

    public byte[] getImage(String id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found"))
                .getData();
    }
}

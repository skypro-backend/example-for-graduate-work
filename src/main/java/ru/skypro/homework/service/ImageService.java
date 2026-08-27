package ru.skypro.homework.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.ImageEntity;
import ru.skypro.homework.repository.ImageRepository;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageEntity saveImage(MultipartFile file) throws IOException {
        byte[] data = file.getBytes();
        String contentType = file.getContentType();
        long fileSize = file.getSize();

        ImageEntity image = new ImageEntity(data, contentType, fileSize);
        return imageRepository.save(image);
    }

    public ImageEntity getImage(Integer id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found with id: " + id));
    }

    public void deleteImage(Integer id) {
        imageRepository.deleteById(id);
    }
}

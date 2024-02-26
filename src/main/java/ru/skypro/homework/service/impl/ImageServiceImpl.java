package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import javax.transaction.Transactional;
import java.io.IOException;
@Transactional
@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;



    @Override
    public ImageEntity uploadImage(MultipartFile imageFile) throws IOException {
        ImageEntity image = new ImageEntity();
        image.setData(imageFile.getBytes());
        image.setFileSize(imageFile.getSize());
        image.setMediaType(imageFile.getContentType());
        image.setData(imageFile.getBytes());
        return imageRepository.save(image);
    }

    @Override
    public ImageEntity getImageById(long id) {
        return null;
    }

    @Override
    public void remove(ImageEntity image) {

    }
}

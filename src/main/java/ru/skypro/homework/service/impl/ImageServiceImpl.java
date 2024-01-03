package ru.skypro.homework.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.exception.ImageNotFoundException;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    @Override
    public Image saveToDataBase(MultipartFile multipartFile) throws IOException {
        Image image = new Image();
        image.setData(multipartFile.getBytes());
        image.setFileSize(multipartFile.getSize());
        image.setMediaType(multipartFile.getContentType());
        return imageRepository.save(image);
    }

    @Override
    public void deleteImage(Image image) {
        imageRepository.delete(image);
    }

    @Override
    public Image getById(Integer id) {
        return imageRepository.findById(id).orElseThrow(ImageNotFoundException::new);
    }

    @Override
    public Image findById(Integer id) {
        return imageRepository.findById(id).orElseThrow(ImageNotFoundException::new);
    }
}
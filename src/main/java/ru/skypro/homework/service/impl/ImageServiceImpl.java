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
    public Image uploadImage(MultipartFile imageFile) {
        Image image = new Image();
        try{
            image.setData(imageFile.getBytes());
            image.setFileSize(imageFile.getSize());
            image.setMediaType(imageFile.getContentType());
        }catch (IOException e){
            e.printStackTrace();
        }
        image = imageRepository.save(image);
        return image;
    }

    @Override
    public void removeImage(Image image) {
        imageRepository.delete(image);

    }

    @Override
    public Image getImage(Long id) {
        return imageRepository.findById(id).orElse(new Image());
    }
}
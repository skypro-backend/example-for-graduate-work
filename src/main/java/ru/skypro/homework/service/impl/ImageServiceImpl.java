package ru.skypro.homework.service.impl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository repository;

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
        image = repository.save(image);
        return image;
    }

    @Override
    public void removeImage(Image image) {
        repository.delete(image);

    }

    @Override
    public Image getImage(Long id) {
        return repository.findById(id).orElse(new Image());
    }
}

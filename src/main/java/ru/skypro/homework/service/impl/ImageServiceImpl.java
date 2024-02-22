package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Override
    public Image uploadImage(long id, MultipartFile imageFile) throws IOException {
        return null;
    }

    @Override
    public void removeImage(Image image) {


    }

    @Override
    public Image getImage(Long id) {
        return null;
    }

}

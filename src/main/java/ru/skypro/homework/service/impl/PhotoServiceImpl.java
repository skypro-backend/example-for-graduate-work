package ru.skypro.homework.service.impl;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.PhotoEntity;
import ru.skypro.homework.service.PhotoService;

import java.io.IOException;

public class PhotoServiceImpl implements PhotoService {


    @Override
    public PhotoEntity saveImage(MultipartFile imageFile) throws IOException {
        return null;
    }

    @Override
    public PhotoEntity getImage(Integer imageId) {
        return null;
    }

    @Override
    public PhotoEntity updateImage(MultipartFile image, Integer imageId) throws IOException {
        return null;
    }

    @Override
    public byte[] getByteFromFile(String path) throws IOException {
        return new byte[0];
    }
}

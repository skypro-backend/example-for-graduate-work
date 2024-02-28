package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.exception.PhotoOnDatabaseIsAbsentException;
import ru.skypro.homework.exception.PhotoOnPcIsAbsentException;
import ru.skypro.homework.entities.PhotoEntity;
import ru.skypro.homework.repositories.PhotoRepository;
import ru.skypro.homework.service.PhotoService;

import java.io.IOException;

@Service

public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository photoRepository;
    private final ImageServiceImpl imageService;

    public PhotoServiceImpl(PhotoRepository photoRepository, ImageServiceImpl imageService) {
        this.photoRepository = photoRepository;
        this.imageService = imageService;
    }

    public byte[] getPhoto(Integer photoId) throws IOException {

        PhotoEntity photo = photoRepository.findById(photoId).orElseThrow(PhotoOnDatabaseIsAbsentException::new);

        //Если картинка запрошенная с ПК не получена по какой-то причине, достаем ее из БД
        if (imageService.getPhotoFromDisk(photo) == null) {
            return photoRepository.findById(photoId).orElseThrow(PhotoOnPcIsAbsentException::new).getData();
        }
        //Если предыдущее условие не выполнилось и с картинкой все в порядке, то достаем ее с ПК
        return imageService.getPhotoFromDisk(photo);
    }

}


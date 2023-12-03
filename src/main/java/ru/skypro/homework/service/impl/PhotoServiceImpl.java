package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import ru.skypro.homework.service.PhotoService;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;

public class PhotoServiceImpl implements PhotoService {
    @Value("${path.to.photos.folder}")
    private String photoDir;
    @Override
    public byte[] getPhoto(Authentication authentication, Integer pk) {
        //формируем путь к картинке в папке проекта
        Path filePath = Path.of(photoDir + "/" + pk);

        return new byte[0];
    }

}

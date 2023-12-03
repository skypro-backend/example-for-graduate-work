package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.model.PhotoEntity;
import ru.skypro.homework.repository.PhotoRepository;
import ru.skypro.homework.service.PhotoService;
import java.nio.file.Path;

@Service
@AllArgsConstructor
public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository photoRepository;
    @Value("${path.to.photos.folder}")
    private String photoDir;
    @Override
    public byte[] getPhoto(Authentication authentication, Integer pk) {
        //формируем путь к картинке в папке проекта
        Path filePath = Path.of(photoDir + "/" + pk);

        return new byte[0];
    }

    public byte[] getPhoto(Integer photoId){
        return photoRepository.findById(photoId).get().getData();
    }

}

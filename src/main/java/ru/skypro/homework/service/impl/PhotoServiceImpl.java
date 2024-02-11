package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.PhotoEntity;
import ru.skypro.homework.exception.PhotoNotFoundException;
import ru.skypro.homework.repository.PhotoRepository;
import ru.skypro.homework.service.PhotoService;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository repository;

    @Override
    public PhotoEntity downloadPhoto(MultipartFile imageFile) throws IOException {
        log.info("Request to avatar upload");
        PhotoEntity image = new PhotoEntity();
        image.setMediaType(imageFile.getContentType());
        image.setData(imageFile.getBytes());
        return repository.save(image);
    }

    @Override
    public void deletePhoto(Long id) {
        log.info("Request to avatar delete by id {}", id);
        repository.deleteById(id);
    }

    @Override
    public byte[] getPhoto(Long id) {
        log.info("Request to avatar by id {}", id);
        return repository.findById(id).orElseThrow(PhotoNotFoundException::new).getData();
    }
}

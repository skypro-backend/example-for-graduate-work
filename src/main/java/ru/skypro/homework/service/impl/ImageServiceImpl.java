package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;
import ru.skypro.homework.models.entity.Images;
import ru.skypro.homework.models.mappers.ImagesMapper;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final ImagesMapper imagesMapper;

    @Override
    public Images getImage(Integer id) {
        log.info("Trying to find an image with id = {}", id);
        return imageRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("The image with id = {} doesn't exist", id);
                    return new NotFoundException("The image with id = " + id + " doesn't exist");
                });
    }

    @Transactional
    @Override
    public void removeImage(Integer id) {
        log.info("Trying to delete an image with id = {}", id);
        getImage(id);
        imageRepository.deleteById(id);
        log.info("The image with id = {} was removed", id);
    }

    @Override
    public Images updateImage(Integer id, MultipartFile mediaTypeImages) {
        Images images = getImages(mediaTypeImages);
        images.setPk(id);
        Images imageSave = imageRepository.save(images);
        log.info("New image was update with id = {}", imageSave.getPk());

        return imageSave;
    }

    @Override
    public Images addImage(MultipartFile mediaTypeImages) {
        Images images = getImages(mediaTypeImages);
        Images imageSave = imageRepository.save(images);
        log.info("New image was saved with id = {}", imageSave.getPk());

        return imageSave;
    }

    private Images getImages(MultipartFile mediaTypeImages) {
        log.info("Trying to add new image");
        Images images;
        try {
            images = imagesMapper.fromFile(mediaTypeImages);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return images;
    }
}

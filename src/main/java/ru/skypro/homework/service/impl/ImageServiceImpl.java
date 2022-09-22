package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;
import ru.skypro.homework.models.entity.Images;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;
import java.io.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageServiceImpl implements ImageService {
    private final ImageRepository repository;

    private final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Override
    public Images findImage(Integer id) {
        logger.info("Trying to find an image with id = {}", id);
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void removeImage(Integer id) {
        logger.info("Trying to delete an image with id = {}", id);
        checkOnExistingImage(id);
        repository.deleteById(id);
        logger.info("The image with id = {} was removed", id);
    }

    public Images addImage(MultipartFile file) throws IOException {
        logger.info("Trying to add new image");
        Images img = new Images();
        img.setFilePath(file.getResource().getFilename());
        img.setFileSize((int) file.getSize());
        img.setMediaType(file.getContentType());
        img.setData(file.getBytes());
        Images image = repository.save(img);
        logger.info("New image was saved with id = {}", image.getPk());
        return image;
    }

    private void checkOnExistingImage(Integer id) {
        if (repository.findById(id).isEmpty()) {
            logger.warn("The image with id = {} doesn't exist", id);
            throw new NotFoundException("The image with id = " + id + " doesn't exist");
        }
    }
}

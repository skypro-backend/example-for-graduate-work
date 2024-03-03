package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;

/**
 * Service for working with images.
 */
public interface ImageService {
    /**
     * Saves the image.
     *
     * @param imageFile image file
     * @return saved image
     */
    Image saveImage(MultipartFile imageFile);

    /**
     * Returns the image by id.
     *
     * @param id image id
     * @return the image by id
     */
    Image getImageById(Long id);
}

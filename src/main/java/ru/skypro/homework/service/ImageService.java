package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
/**
 * CRUD-methods for managing all images on platform (Avatars and adds Images)
 */
public interface ImageService<T> {

    /**
     * Method to upload the image on the platform
     * @param file The file of the image
     * @return Returns saved image
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     */
    T uploadImage(MultipartFile file) throws IOException;

    /**
     * Method to get images by ID
     * @param id ID of an image
     * @return Returns the found image
     */
    T getImageById(Integer id);

    /**
     * Method to remove the image
     * @param object DTO of the image (Avatar or Add)
     */
    void remove(T object);
}

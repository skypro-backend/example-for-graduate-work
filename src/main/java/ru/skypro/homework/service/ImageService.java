package ru.skypro.homework.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.ImageEntity;
import ru.skypro.homework.repository.ImageRepository;

import java.io.IOException;

/**
 * Сервис для работы с изображениями.
 * Обеспечивает сохранение, получение и удаление изображений в базе данных.
 * Основные функции:
 * - сохранение изображения из MultipartFile в БД;
 * - получение изображения по ID;
 * - удаление изображения по ID.
 */
@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    /**
     * Сохранить изображение в базу данных.
     * Извлекает байтовый массив, тип содержимого и размер файла из MultipartFile.
     * @param file загружаемый файл изображения
     * @return сохранённая сущность {@link ImageEntity}
     * @throws IOException если ошибка при чтении файла
     */
    public ImageEntity saveImage(MultipartFile file) throws IOException {
        byte[] data = file.getBytes();
        String contentType = file.getContentType();
        long fileSize = file.getSize();

        ImageEntity image = new ImageEntity(data, contentType, fileSize);
        return imageRepository.save(image);
    }

    /**
     * Получить изображение по ID.
     * @param id ID изображения
     * @return сущность {@link ImageEntity}
     * @throws RuntimeException если изображение не найдено
     */
    public ImageEntity getImage(Integer id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found with id: " + id));
    }

    public void deleteImage(Integer id) {
        imageRepository.deleteById(id);
    }
}

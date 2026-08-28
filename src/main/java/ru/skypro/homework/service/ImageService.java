package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Сервис для работы с изображениями.
 * Предоставляет методы для сохранения, получения и удаления изображений в файловой системе.
 */
public interface ImageService {

    /**
     * Сохраняет изображение в файловой системе.
     *
     * @param file         файл с изображением
     * @param subDirectory поддиректория для сохранения
     * @param fileName     имя файла (без расширения)
     * @return относительный путь к сохраненному файлу
     * @throws RuntimeException если не удалось сохранить файл
     */
    String saveImage(MultipartFile file, String subDirectory, String fileName);

    /**
     * Получает изображение из файловой системы по его пути.
     *
     * @param filePath путь к файлу
     * @return массив байтов изображения или null, если файл не найден
     */
    byte[] getImage(String filePath);

    /**
     * Удаляет изображение из файловой системы.
     *
     * @param filePath путь к файлу
     */
    void deleteImage(String filePath);

    /**
     * Формирует URL для доступа к изображению.
     *
     * @param filePath путь к файлу
     * @return URL для доступа к изображению или null, если путь пустой
     */
    String getImageUrl(String filePath);
}

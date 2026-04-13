package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

    /**
     * Сервис для работы с изображениями
     * <p>
     * Предоставляет методы для сохранения и получения изображений
     * (аватаров пользователей и картинок объявлений)
     * </p>
     */
    public interface ImageService {

        /**
         * Сохраняет изображение аватара пользователя
         *
         * @param image файл изображения
         * @param email email пользователя (для формирования имени файла)
         * @return путь к сохраненному изображению
         */
        String saveAvatarImage(MultipartFile image, String email);

        /**
         * Сохраняет изображение объявления
         *
         * @param image файл изображения
         * @param adId  ID объявления (для формирования имени файла)
         * @return путь к сохраненному изображению
         */
        String saveAdImage(MultipartFile image, Integer adId);

        /**
         * Сохраняет изображение на диск
         *
         * @param image     файл изображения
         * @param directory директория для сохранения
         * @param baseName  базовое имя файла
         * @return путь к сохраненному изображению
         */
        String saveImage(MultipartFile image, String directory, String baseName);

        /**
         * Получает изображение по пути
         *
         * @param imagePath путь к изображению
         * @return массив байтов изображения или null, если файл не найден
         */
        byte[] getImage(String imagePath);

        /**
         * Удаляет изображение по пути
         *
         * @param imagePath путь к изображению
         */
        void deleteImage(String imagePath);
    }
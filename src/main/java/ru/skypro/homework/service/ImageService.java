package ru.skypro.homework.service;

import ru.skypro.homework.controller.AdController;
import ru.skypro.homework.controller.ImageController;
import ru.skypro.homework.controller.UserController;
import ru.skypro.homework.exception.EntityNotFoundException;
import ru.skypro.homework.service.impl.ImageServiceImpl;

import java.io.IOException;

/**
 * Сервис для хранения и получения изображений объявлений и пользователей.
 * <p>
 * Определяет контракт работы с файлами изображений:
 * <ul>
 *   <li>Сохранение изображения объявления по его ID</li>
 *   <li>Сохранение аватара пользователя по его ID</li>
 *   <li>Получение изображения объявления для отображения</li>
 *   <li>Получение аватара пользователя для профиля и комментариев</li>
 * </ul>
 * Все методы работают с байтами изображений и выбрасывают {@link IOException} при ошибках ввода-вывода.
 * <p>
 * Реализация ({@link ImageServiceImpl}) сохраняет файлы в директории, указанной в конфигурации
 * (по умолчанию: {@code /uploads/images}), с именами:
 * <ul>
 *   <li>{@code ad_{id}.jpg}</li>
 *   <li>{@code user_{id}.jpg}</li>
 * </ul>
 * При отсутствии файла возвращает заглушку ({@code ad_default.jpg}, {@code user_default.jpg}),
 * если она существует.
 *
 * @see ImageServiceImpl — реализация сервиса, работающая с файловой системой
 * @see AdController — использует {@code saveAdImage} при создании/обновлении объявления
 * @see UserController — использует {@code saveUserImage} при обновлении аватара
 * @see ImageController — использует {@code getAdImage} и {@code getUserImage} для отдачи изображений фронтенду
 */
public interface ImageService {

    /**
     * Сохраняет изображение объявления на диск.
     * <p>
     * Файл сохраняется как {@code ad_{id}.jpg}. Если файл уже существует — перезаписывается.
     *
     * @param adId       идентификатор объявления
     * @param imageBytes байты изображения (например, JPEG)
     * @throws IOException если запись файла невозможна (нет прав, диск полон и т.п.)
     */
    void saveAdImage(Integer adId, byte[] imageBytes) throws IOException;

    /**
     * Сохраняет аватар пользователя на диск.
     * <p>
     * Файл сохраняется как {@code user_{id}.jpg}. Если файл уже существует — перезаписывается.
     *
     * @param userId     идентификатор пользователя
     * @param imageBytes байты изображения (например, JPEG)
     * @throws IOException если запись файла невозможна
     */
    void saveUserImage(Integer userId, byte[] imageBytes) throws IOException;

    /**
     * Получает изображение объявления по его идентификатору.
     * <p>
     * Возвращает байты изображения для передачи клиенту.
     * Если файл не найден — возвращает заглушку {@code ad_default.jpg}.
     *
     * @param adId идентификатор объявления
     * @return байты изображения в формате JPEG
     * @throws IOException             при ошибке чтения файла
     * @throws EntityNotFoundException если изображение и заглушка отсутствуют
     */
    byte[] getAdImage(Integer adId) throws IOException;

    /**
     * Получает аватар пользователя по его идентификатору.
     * <p>
     * Возвращает байты изображения для отображения в профиле или комментариях.
     * Если файл не найден — возвращает заглушку {@code user_default.jpg}.
     *
     * @param userId идентификатор пользователя
     * @return байты изображения в формате JPEG
     * @throws IOException             при ошибке чтения файла
     * @throws EntityNotFoundException если аватар и заглушка отсутствуют
     */
    byte[] getUserImage(Integer userId) throws IOException;
}
package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.skypro.homework.controller.AdController;
import ru.skypro.homework.controller.ImageController;
import ru.skypro.homework.controller.UserController;
import ru.skypro.homework.exception.EntityNotFoundException;
import ru.skypro.homework.service.ImageService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.*;

/**
 * Реализация сервиса для хранения и получения изображений объявлений и пользователей.
 * <p>
 * Сохраняет файлы в указанной директории (по умолчанию: {@code /uploads/images}) с именами:
 * <ul>
 *   <li>{@code ad_{id}.jpg} — изображение объявления</li>
 *   <li>{@code user_{id}.jpg} — аватар пользователя</li>
 * </ul>
 * При отсутствии изображения возвращает заглушку ({@code ad_default.jpg}, {@code user_default.jpg}),
 * если она существует. Используется в связке с:
 * <ul>
 *   <li>{@link ru.skypro.homework.controller.AdController} — при создании и обновлении объявлений</li>
 *   <li>{@link ru.skypro.homework.controller.UserController} — при обновлении аватара пользователя</li>
 *   <li>{@link ru.skypro.homework.controller.ImageController} — при отдаче изображений фронтенду</li>
 * </ul>
 *
 * @see ImageService — основной интерфейс, используемый контроллерами
 * @see AdController — вызывает {@code saveAdImage} при создании/обновлении объявления
 * @see UserController — вызывает {@code saveUserImage} при обновлении профиля
 * @see ImageController — вызывает {@code getAdImage}, {@code getUserImage} для отдачи файлов
 */
@Service
public class ImageServiceImpl implements ImageService {

    /**
     * Путь к папке для хранения изображений, задаётся через application.properties.
     * По умолчанию: "/uploads/images".
     */
    @Value("${image.folder.path:/uploads/images}")
    private String imagePath;

    /**
     * Объект пути к директории с изображениями, инициализируется при старте приложения.
     */
    private Path imageDir;

    /**
     * Логгер для отслеживания операций чтения и записи изображений.
     */
    private static final Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);

    /**
     * Инициализирует директорию для хранения изображений.
     * Создаёт папку, если она не существует.
     *
     * @throws IOException если не удаётся создать директорию
     */
    @PostConstruct
    public void init() throws IOException {
        imageDir = Paths.get(imagePath);
        Files.createDirectories(imageDir);
    }

    /**
     * Сохраняет изображение объявления на диск.
     * <p>
     * Файл сохраняется как {@code ad_{id}.jpg}. Если файл уже существует — перезаписывается.
     * Вызывается из {@link ru.skypro.homework.service.impl.AdServiceImpl#updateImage(Integer, byte[], String)}.
     *
     * @param adId       идентификатор объявления
     * @param imageBytes байты изображения (например, JPEG)
     * @throws IOException если запись файла невозможна
     */
    @Override
    public void saveAdImage(Integer adId, byte[] imageBytes) throws IOException {
        Path path = imageDir.resolve("ad_" + adId + ".jpg");
        Files.write(path, imageBytes, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    /**
     * Сохраняет аватар пользователя на диск.
     * <p>
     * Файл сохраняется как {@code user_{id}.jpg}. Если файл уже существует — перезаписывается.
     * Вызывается из {@link ru.skypro.homework.service.impl.UserServiceImpl#updateUserImage(String, byte[])}.
     *
     * @param userId     идентификатор пользователя
     * @param imageBytes байты изображения (например, JPEG)
     * @throws IOException если запись файла невозможна
     */
    @Override
    public void saveUserImage(Integer userId, byte[] imageBytes) throws IOException {
        Path path = imageDir.resolve("user_" + userId + ".jpg");
        Files.write(path, imageBytes, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    /**
     * Получает изображение объявления по его идентификатору.
     * <p>
     * Сначала ищет файл {@code ad_{id}.jpg}. Если не найден — возвращает {@code ad_default.jpg}.
     * Если и заглушка отсутствует — выбрасывает исключение.
     * Вызывается из {@link ru.skypro.homework.controller.ImageController#getAdImage(Integer)}.
     *
     * @param adId идентификатор объявления
     * @return байты изображения
     * @throws IOException             если ошибка при чтении файла
     * @throws EntityNotFoundException если изображение и заглушка отсутствуют
     */
    @Override
    public byte[] getAdImage(Integer adId) throws IOException {
        Path path = imageDir.resolve("ad_" + adId + ".jpg");
        if (!Files.exists(path)) {
            log.warn("Image for ad {} not found, using default", adId);
            path = imageDir.resolve("ad_default.jpg");
            if (!Files.exists(path)) {
                log.error("Default ad image not found in directory: {}", imageDir);
                throw new EntityNotFoundException("Изображение объявления не найдено");
            }
        }
        return Files.readAllBytes(path);
    }

    /**
     * Получает аватар пользователя по его идентификатору.
     * <p>
     * Сначала ищет файл {@code user_{id}.jpg}. Если не найден — возвращает {@code user_default.jpg}.
     * Если и заглушка отсутствует — выбрасывает исключение.
     * Вызывается из {@link ru.skypro.homework.controller.ImageController#getUserImage(Integer)}.
     *
     * @param userId идентификатор пользователя
     * @return байты изображения
     * @throws IOException             если ошибка при чтении файла
     * @throws EntityNotFoundException если аватар и заглушка отсутствуют
     */
    @Override
    public byte[] getUserImage(Integer userId) throws IOException {
        Path path = imageDir.resolve("user_" + userId + ".jpg");
        if (!Files.exists(path)) {
            path = imageDir.resolve("user_default.jpg");
            if (!Files.exists(path)) {
                throw new EntityNotFoundException("Аватар пользователя не найден");
            }
        }
        return Files.readAllBytes(path);
    }
}
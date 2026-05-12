package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.exception.EntityNotFoundException;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;

/**
 * Контроллер для получения изображений объявлений и пользователей.
 * <p>
 * Обеспечивает доступ к изображениям по URL-адресам:
 * <ul>
 *   <li>{@code GET /image/ad/{id}} — возвращает изображение объявления</li>
 *   <li>{@code GET /image/user/{id}} — возвращает аватар пользователя</li>
 * </ul>
 * Эти пути соответствуют значениям полей {@code image} в DTO {@link ru.skypro.homework.dto.Ad}
 * и {@link ru.skypro.homework.dto.User}, которые используются фронтендом для отображения изображений.
 * <p>
 * Контроллер работает в паре с {@link ImageService}, который отвечает за чтение файлов
 * из директории (например, {@code uploads/images/ad_4.jpg}), а при их отсутствии —
 * возврат заглушки (например, {@code ad_default.jpg}).
 * <p>
 * Этот контроллер необходим, потому что:
 * <ul>
 *   <li>Фронтенд ожидает получать изображения по этим URL</li>
 *   <li>Спецификация OpenAPI не описывает эти GET-запросы напрямую, но подразумевает их через поля {@code image}</li>
 *   <li>Без этого контроллера изображения не отображаются на сайте</li>
 * </ul>
 *
 * @see ImageService — сервис для чтения изображений с диска
 * @see ru.skypro.homework.mapper.AdMapper — формирует ссылку {@code /image/ad/{id}}
 * @see ru.skypro.homework.mapper.UserMapper — формирует ссылку {@code /image/user/{id}}
 */
@RestController
@RequestMapping("/image")
@Tag(name = "Изображения", description = "Получение изображений объявлений и пользователей")
public class ImageController {

    /**
     * Сервис для работы с изображениями: чтение, сохранение, обработка.
     */
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * Получение изображения объявления по его идентификатору.
     * <p>
     * Возвращает байты изображения в формате JPEG. Если файл не найден,
     * возвращается 404. Используется фронтендом для отображения картинки в карточке объявления.
     *
     * @param id идентификатор объявления
     * @return {@link ResponseEntity} с изображением и заголовком {@code Content-Type: image/jpeg},
     * или статус 404, если изображение не найдено
     */
    @Operation(summary = "Получение изображения объявления")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "image/jpeg"))
    @ApiResponse(responseCode = "404", description = "Изображение не найдено")
    @GetMapping("/ad/{id}")
    public ResponseEntity<byte[]> getAdImage(@PathVariable Integer id) {
        try {
            byte[] image = imageService.getAdImage(id);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE).body(image);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Получение аватара пользователя по его идентификатору.
     * <p>
     * Возвращает байты аватара в формате JPEG. Если файл не найден,
     * возвращается 404. Используется фронтендом для отображения аватарок
     * в комментариях и профилях.
     *
     * @param id идентификатор пользователя
     * @return {@link ResponseEntity} с изображением и заголовком {@code Content-Type: image/jpeg},
     * или статус 404, если аватар не найден
     */
    @Operation(summary = "Получение аватара пользователя")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "image/jpeg"))
    @ApiResponse(responseCode = "404", description = "Аватар не найден")
    @GetMapping("/user/{id}")
    public ResponseEntity<byte[]> getUserImage(@PathVariable Integer id) {
        try {
            byte[] image = imageService.getUserImage(id);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE).body(image);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
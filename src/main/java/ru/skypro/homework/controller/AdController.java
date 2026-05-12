package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.exception.EntityNotFoundException;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;

/**
 * Контроллер для управления объявлениями.
 * <p>
 * Обеспечивает полный цикл работы с объявлениями (CRUD):
 * <ul>
 *   <li>Получение списка и деталей объявлений</li>
 *   <li>Создание, обновление и удаление</li>
 *   <li>Управление изображениями объявлений</li>
 * </ul>
 * Все операции, кроме получения списка и просмотра отдельного объявления,
 * требуют аутентификации пользователя.
 * <p>
 * Поддерживает формат {@code multipart/form-data} для загрузки изображений.
 *
 * @see AdService — бизнес-логика объявлений
 * @see ImageService — хранение и обработка изображений
 */
@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/ads")
@Tag(name = "Объявления", description = "API для управления объявлениями")
@RequiredArgsConstructor
public class AdController {

    /**
     * Логгер для отладки и мониторинга операций контроллера.
     */
    private static final Logger log = LoggerFactory.getLogger(AdController.class);

    /**
     * Сервис для выполнения бизнес-операций с объявлениями.
     */
    private final AdService adService;

    /**
     * Сервис для работы с изображениями объявлений.
     */
    private final ImageService imageService;

    /**
     * Получение всех объявлений.
     * <p>
     * Доступно без авторизации. Возвращает список краткой информации
     * обо всех объявлениях в системе.
     *
     * @return {@link ResponseEntity} с объектом {@link Ads}, содержащим список объявлений
     * @apiNote HTTP GET /ads → 200 OK
     */
    @Operation(summary = "Получение всех объявлений", description = "Возвращает список всех объявлений")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Ads.class)))
    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }

    /**
     * Получение расширенной информации об объявлении по его идентификатору.
     * <p>
     * Доступно без авторизации. Возвращает полные данные:
     * автор, описание, контакты, цена, заголовок и ссылку на изображение.
     *
     * @param id идентификатор объявления
     * @return {@link ResponseEntity} с объектом {@link ExtendedAd}
     * или 404, если объявление не найдено
     */
    @Operation(summary = "Получение информации об объявлении", description = "Возвращает полную информацию об объявлении по ID")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ExtendedAd.class)))
    @ApiResponse(responseCode = "404", description = "Объявление не найдено")
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAd(@PathVariable Integer id) {
        try {
            ExtendedAd extendedAd = adService.getExtendedAd(id);
            return ResponseEntity.ok(extendedAd);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Создание нового объявления.
     * <p>
     * Требует авторизации. Принимает данные в формате {@code multipart/form-data},
     * где:
     * <ul>
     *   <li>{@code properties} — JSON с заголовком, ценой и описанием</li>
     *   <li>{@code image} — бинарный файл изображения</li>
     * </ul>
     * После создания объявления сохраняется его изображение.
     *
     * @param propertiesJson строка JSON с данными объявления
     * @param image          файл изображения (JPG)
     * @param authentication текущий аутентифицированный пользователь
     * @return {@link ResponseEntity} с созданным объектом {@link Ad} и статусом 201,
     * или 400 при ошибке данных, или 500 при внутренней ошибке
     */
    @Operation(summary = "Добавление объявления", description = "Создаёт новое объявление. Требуется авторизация. " + "Формат: multipart/form-data с полями 'properties' (JSON) и 'image' (файл).")
    @ApiResponse(responseCode = "201", description = "Объявление создано", content = @Content(schema = @Schema(implementation = Ad.class)))
    @ApiResponse(responseCode = "400", description = "Некорректные данные")
    @ApiResponse(responseCode = "401", description = "Неавторизован")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ad> createAd(@Parameter(description = "Данные объявления в формате JSON", required = true) @RequestPart("properties") String propertiesJson,

                                       @Parameter(description = "Изображение объявления", required = true) @RequestPart("image") MultipartFile image,

                                       Authentication authentication) {

        try {
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            CreateOrUpdateAd createAd = mapper.readValue(propertiesJson, CreateOrUpdateAd.class);

            AdEntity adEntity = new AdEntity();
            adEntity.setTitle(createAd.getTitle());
            adEntity.setPrice(createAd.getPrice());
            adEntity.setDescription(createAd.getDescription());

            Ad createdAd = adService.createAd(adEntity, authentication.getName());

            if (createdAd.getPk() == null) {
                log.error("Created Ad has null pk!");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

            log.debug("Created Ad with pk={}", createdAd.getPk());

            imageService.saveAdImage(createdAd.getPk(), image.getBytes());

            return ResponseEntity.status(HttpStatus.CREATED).body(createdAd);
        } catch (IOException e) {
            log.error("Failed to save image", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            log.error("Unexpected error in createAd", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Удаление объявления по идентификатору.
     * <p>
     * Доступно только автору объявления или администратору.
     *
     * @param id             идентификатор объявления
     * @param authentication текущий пользователь
     * @return {@link ResponseEntity} с пустым телом и статусом 204 при успехе,
     * 404 — если объявление не найдено,
     * 403 — если нет прав на удаление
     */
    @Operation(summary = "Удаление объявления", description = "Удаляет объявление. Только автор или админ")
    @ApiResponse(responseCode = "204", description = "Объявление удалено")
    @ApiResponse(responseCode = "403", description = "Нет прав")
    @ApiResponse(responseCode = "404", description = "Объявление не найдено")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAd(@Parameter(description = "ID объявления", required = true) @PathVariable Integer id, Authentication authentication) {
        try {
            adService.deleteAd(id, authentication.getName());
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    /**
     * Частичное обновление данных объявления.
     * <p>
     * Доступно только автору или администратору. Принимает JSON с полями:
     * заголовок, цена, описание.
     *
     * @param id             идентификатор объявления
     * @param updateAd       объект с новыми данными
     * @param authentication текущий пользователь
     * @return {@link ResponseEntity} с обновлённым объектом {@link Ad},
     * 404 — если объявление не найдено,
     * 403 — если нет прав
     */
    @Operation(summary = "Обновление объявления", description = "Изменяет данные объявления. Только автор или админ")
    @ApiResponse(responseCode = "200", description = "Объявление обновлено", content = @Content(schema = @Schema(implementation = Ad.class)))
    @ApiResponse(responseCode = "403", description = "Нет прав")
    @ApiResponse(responseCode = "404", description = "Объявление не найдено")
    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ad> updateAd(@Parameter(description = "ID объявления", required = true) @PathVariable Integer id,

                                       @RequestBody CreateOrUpdateAd updateAd, Authentication authentication) {

        try {
            AdEntity updatedEntity = new AdEntity();
            updatedEntity.setTitle(updateAd.getTitle());
            updatedEntity.setPrice(updateAd.getPrice());
            updatedEntity.setDescription(updateAd.getDescription());

            Ad updated = adService.updateAd(id, updatedEntity, authentication.getName());
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    /**
     * Получение всех объявлений текущего пользователя.
     * <p>
     * Доступно только авторизованным пользователям.
     *
     * @param authentication текущий пользователь
     * @return {@link ResponseEntity} со списком объявлений пользователя
     */
    @Operation(summary = "Мои объявления", description = "Возвращает список объявлений текущего пользователя")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Ads.class)))
    @ApiResponse(responseCode = "401", description = "Неавторизован")
    @GetMapping("/me")
    public ResponseEntity<Ads> getAdsMe(Authentication authentication) {
        Ads ads = adService.getAdsByUser(authentication.getName());
        return ResponseEntity.ok(ads);
    }

    /**
     * Обновление изображения объявления.
     * <p>
     * Доступно только автору или администратору. Принимает новый файл изображения.
     *
     * @param id             идентификатор объявления
     * @param image          новый файл изображения
     * @param authentication текущий пользователь
     * @return {@link ResponseEntity} с пустым телом и статусом 200 при успехе,
     * 400 — ошибка чтения файла,
     * 404 — объявление не найдено,
     * 403 — нет прав
     */
    @Operation(summary = "Обновление изображения объявления", description = "Заменяет изображение объявления. Только автор или админ")
    @ApiResponse(responseCode = "200", description = "Изображение обновлено")
    @ApiResponse(responseCode = "400", description = "Ошибка при загрузке файла")
    @ApiResponse(responseCode = "403", description = "Нет прав")
    @ApiResponse(responseCode = "404", description = "Объявление не найдено")
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateAdImage(@Parameter(description = "ID объявления", required = true) @PathVariable Integer id,

                                           @Parameter(description = "Новое изображение", required = true) @RequestPart("image") MultipartFile image,

                                           Authentication authentication) {

        try {
            adService.updateImage(id, image.getBytes(), authentication.getName());
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
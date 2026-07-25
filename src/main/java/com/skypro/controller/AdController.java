package com.skypro.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skypro.dto.Ad;
import com.skypro.dto.Ads;
import com.skypro.dto.CreateOrUpdateAd;
import com.skypro.dto.ExtendedAd;
import com.skypro.service.AdService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * REST-контроллер для управления объявлениями.
 * <p>
 * Предоставляет эндпоинты для получения списка объявлений, создания,
 * редактирования, удаления, а также для работы с картинками объявлений.
 * </p>
 */
@RestController
@RequestMapping("/ads")
public class AdController {

    private static final Logger log = LoggerFactory.getLogger(AdController.class);

    private final AdService adService;
    private final ObjectMapper objectMapper;

    public AdController(AdService adService, ObjectMapper objectMapper) {
        this.adService = adService;
        this.objectMapper = objectMapper;
    }

    /**
     * Возвращает список всех объявлений.
     * <p>
     * Доступен без авторизации.
     * </p>
     *
     * @return объект {@link Ads} с количеством и списком объявлений
     */
    @Operation(summary = "Получение всех объявлений", operationId = "getAllAds")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Ads.class)))
    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        log.info("GET /ads called");
        Ads ads = adService.getAllAds();
        log.info("GET /ads returning {} ads", ads.getCount());
        return ResponseEntity.ok(ads);
    }

    /**
     * Создаёт новое объявление с изображением.
     * <p>
     * Требует авторизации. Данные передаются как multipart/form-data:
     * <ul>
     *   <li>{@code properties} — JSON-строка с заголовком, ценой и описанием</li>
     *   <li>{@code image} — файл изображения</li>
     * </ul>
     * </p>
     *
     * @param propertiesJson   JSON-строка с данными объявления
     * @param image            файл изображения
     * @param authentication   объект аутентификации текущего пользователя
     * @return созданное объявление в виде {@link Ad} со статусом 201 Created
     * @throws JsonProcessingException если {@code propertiesJson} не является валидным JSON
     */
    @Operation(summary = "Добавление объявления", operationId = "addAd")
    @ApiResponse(responseCode = "201", description = "Created",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Ad.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ad> addAd(
            @RequestPart("properties") String propertiesJson,
            @RequestParam("image") MultipartFile image,
            Authentication authentication) throws JsonProcessingException {
        CreateOrUpdateAd createOrUpdateAd = objectMapper.readValue(propertiesJson, CreateOrUpdateAd.class);
        Ad ad = adService.addAd(createOrUpdateAd, image, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(ad);
    }

    /**
     * Возвращает детальную информацию об объявлении по его идентификатору.
     * <p>
     * Доступен без авторизации.
     * </p>
     *
     * @param id идентификатор объявления
     * @return объект {@link ExtendedAd} с полной информацией об объявлении и авторе
     */
    @Operation(summary = "Получение информации об объявлении", operationId = "getAds")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ExtendedAd.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Not found")
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAd(@PathVariable Integer id) {
        return ResponseEntity.ok(adService.getAd(id));
    }

    /**
     * Удаляет объявление по идентификатору.
     * <p>
     * Доступно только владельцу объявления или администратору.
     * </p>
     *
     * @param id идентификатор объявления
     * @return статус 204 No Content
     */
    @Operation(summary = "Удаление объявления", operationId = "removeAd")
    @ApiResponse(responseCode = "204", description = "No Content")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAd(@PathVariable Integer id) {
        adService.removeAd(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Обновляет данные существующего объявления.
     * <p>
     * Доступно только владельцу объявления или администратору.
     * </p>
     *
     * @param id               идентификатор объявления
     * @param createOrUpdateAd новые данные (заголовок, цена, описание)
     * @return обновлённое объявление в виде {@link Ad}
     */
    @Operation(summary = "Обновление информации об объявлении", operationId = "updateAds")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Ad.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not found")
    @PatchMapping("/{id}")
    public ResponseEntity<Ad> updateAd(@PathVariable Integer id,
                                       @RequestBody CreateOrUpdateAd createOrUpdateAd) {
        return ResponseEntity.ok(adService.updateAd(id, createOrUpdateAd));
    }

    /**
     * Возвращает список объявлений, принадлежащих текущему пользователю.
     * <p>
     * Требует авторизации.
     * </p>
     *
     * @param authentication объект аутентификации текущего пользователя
     * @return объект {@link Ads} с объявлениями пользователя
     */
    @Operation(summary = "Получение объявлений авторизованного пользователя", operationId = "getAdsMe")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Ads.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @GetMapping("/me")
    public ResponseEntity<Ads> getAdsMe(Authentication authentication) {
        String username = authentication.getName();
        log.info("getAdsMe called for user: {}", username);
        Ads ads = adService.getAdsMe(username);
        log.info("getAdsMe returning {} ads for user: {}", ads.getCount(), username);
        return ResponseEntity.ok(ads);
    }

    /**
     * Обновляет изображение объявления.
     * <p>
     * Доступно только владельцу объявления или администратору.
     * </p>
     *
     * @param id    идентификатор объявления
     * @param image новый файл изображения
     * @return пустой массив байтов со статусом 200 OK
     */
    @Operation(summary = "Обновление картинки объявления", operationId = "updateImage")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = "application/octet-stream"))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not found")
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateImage(@PathVariable Integer id,
                                              @RequestParam("image") MultipartFile image) {
        adService.updateImage(id, image);
        return ResponseEntity.ok(new byte[0]);
    }
}
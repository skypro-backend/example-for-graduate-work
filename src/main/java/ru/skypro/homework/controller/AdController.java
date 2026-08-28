package ru.skypro.homework.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.UserService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * Контроллер для управления объявлениями.
 * Обрабатывает все HTTP-запросы, связанные с объявлениями.
 * Основные функции:
 * - получение списка всех объявлений;
 * - создание, обновление и удаление объявлений;
 * - получение объявлений текущего пользователя;
 * - загрузка и обновление картинок объявлений.
 */

@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
@Tag(name = "Объявления")
public class AdController {

    private final AdService adService;
    private final UserService userService;

    /**
     * Получить все объявления.
     * Доступно без авторизации.
     * @return список всех объявлений с общим количеством
     */
    @GetMapping
    @Operation(summary = "Получение всех объявлений")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<Ads> getAllAds() {
        List<Ad> ads = adService.getAllAds();
        Ads response = new Ads();
        response.setCount(ads.size());
        response.setResults(ads);
        return ResponseEntity.ok(response);
    }

    /**
     * Создать новое объявление.
     * Только для авторизованных пользователей (USER или ADMIN).
     * @param properties      данные объявления (заголовок, цена, описание)
     * @param image           файл картинки объявления
     * @param authentication  данные авторизованного пользователя
     * @return созданное объявление с ID
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Добавление объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<Ad> addAd(
            @RequestPart("properties") @Valid CreateOrUpdateAd properties,
            @RequestPart("image") MultipartFile image,
        Authentication authentication) {

        String email = authentication.getName();
        return userService.getUserByEmail(email)
                .flatMap(user -> {
                    try {
                        return adService.createAd(properties, image, user.getId());
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to create ad", e);
                    }
                })
                .map(ad -> ResponseEntity.status(HttpStatus.CREATED).body(ad))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    /**
     * Получить расширенную информацию об объявлении по ID.
     * Доступно без авторизации.
     * @param id ID объявления
     * @return расширенная информация об объявлении (включая данные автора)
     */
    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об объявлении")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<ExtendedAd> getAd(@PathVariable Integer id) {
        return adService.getAdById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Удалить объявление по ID.
     * Только для автора объявления или администратора.
     * @param id             ID объявления
     * @param authentication данные авторизованного пользователя
     * @return 204 No Content при успешном удалении, 404 если объявление не найдено
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<Void> deleteAd(@PathVariable Integer id, Authentication authentication) {
        boolean deleted = adService.deleteAd(id, authentication);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Обновить данные объявления.
     * Только для автора объявления или администратора.
     * @param id             ID объявления
     * @param ad             новые данные объявления
     * @param authentication данные авторизованного пользователя
     * @return обновлённое объявление
     */
    @PatchMapping("/{id}")
    @Operation(summary = "Обновление информации об объявлении")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<Ad> updateAd(@PathVariable Integer id,
                                       @Valid @RequestBody CreateOrUpdateAd ad,
                                       Authentication authentication) {
        return adService.updateAd(id, ad, authentication)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Получить все объявления текущего авторизованного пользователя.
     * @param authentication данные авторизованного пользователя
     * @return список объявлений пользователя
     */
    @GetMapping("/me")
    @Operation(summary = "Получение объявлений авторизованного пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<Ads> getAdsMe(Authentication authentication) {
        String email = authentication.getName();
        return userService.getUserByEmail(email)
                .map(user -> {
                    List<Ad> ads = adService.getAdsByUser(user.getId());
                    Ads response = new Ads();
                    response.setCount(ads.size());
                    response.setResults(ads);
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    /**
     * Обновить картинку объявления.
     * Только для автора объявления или администратора.
     * @param id             ID объявления
     * @param image          новый файл картинки
     * @param authentication данные авторизованного пользователя
     * @return байтовый массив обновлённой картинки
     * @throws IOException если ошибка при сохранении файла
     */
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление картинки объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<byte[]> updateImage(@PathVariable Integer id,
                                            @RequestParam("image") MultipartFile image,
                                            Authentication authentication) throws IOException {
        byte[] updatedImageData = adService.updateAdImage(id, image, authentication);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(updatedImageData);
    }
}

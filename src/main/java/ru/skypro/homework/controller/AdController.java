package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.ad.Ad;
import ru.skypro.homework.ad.Ads;
import ru.skypro.homework.ad.CreateOrUpdateAd;
import ru.skypro.homework.ad.ExtendedAd;
import ru.skypro.homework.service.AdService;

import javax.validation.Valid;
import java.util.Map;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

    //      * REST-контроллер для управления объявлениями.
    //      * Предоставляет эндпоинты для создания, чтения, обновления и удаления товаров.
    //      * Все операции записи требуют Basic-аутентификации.

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@Tag(name = "Объявления")
public class AdController {

    private final AdService adService;

    //      * Возвращает пагинированный список всех объявлений.
    //      * @param offset смещение (номер страницы)
    //      * @param limit количество элементов на странице
    //      * @return коллекцию объявлений Ads

    @GetMapping
    @Operation(summary = "Получение всех объявлений")
    public ResponseEntity<Ads> getAllAds(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(adService.getAllAds(offset, limit));
    }

    //     * Возвращает список объявлений текущего авторизованного пользователя.
    //     * @param auth объект аутентификации Spring Security
    //     * @return коллекцию собственных объявлений пользователя

    @GetMapping("/me")
    @Operation(summary = "Получение моих объявлений")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Ads> getMyAds(Authentication auth) {
        return ResponseEntity.ok(adService.getMyAds(auth));
    }

    //      * Получает подробную информацию об объявлении по идентификатору.
    //      * @param id уникальный идентификатор объявления (pk)
    //      * @return Optional с расширенной информацией ExtendedAd

    @GetMapping("/{id}")
    @Operation(summary = "Просмотр объявления")
    public ResponseEntity<ExtendedAd> getAdById(@PathVariable long id) {
        return adService.getAdById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //     * Создает новое объявление от имени текущего пользователя.
    //     * Сохраняет изображение во временную директорию сервера, если файл передан.
    //     * @param Data параметры объявления (заголовок, цена, описание)
    //     * @param image бинарные данные картинки (может быть null)
    //     * @param auth данные аутентификации автора
    //     * @return созданный объект Ad

    @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
    public Ad createAd(
            @RequestPart(value = "properties", required = true) @Valid CreateOrUpdateAd data,
            @RequestPart(value = "image", required = false) MultipartFile image,
            Authentication auth) {

        return adService.createAd(data, image, auth);
    }

    //     * Обновляет основное изображение объявления.
    //     * @param id идентификатор объявления
    //     * @param image новый файл изображения
    //     * @param auth данные аутентификации владельца

    @PatchMapping(value = "/{id}/image", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateImage(
            @PathVariable long id,
            @RequestPart MultipartFile image,
            Authentication auth) {

        adService.updateImage(id, image, auth);
        return ResponseEntity.ok().build();
    }

    //     * Обновляет существующее объявление.
    //     * Проверяет права доступа: изменить может только владелец или ADMIN.
    //     * @param id идентификатор редактируемого объявления
    //     * @param data новые значения полей
    //     * @param auth данные аутентификации
    //     * @return обновленный объект Ad

    @PatchMapping("/{id}")
    @Operation(summary = "Редактирование объявления")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<Ad> updateAd(
            @PathVariable long id,
            @RequestBody CreateOrUpdateAd data,
            Authentication auth) {
        return ResponseEntity.ok(adService.updateAd(id, data, auth));
    }

    //     * Удаляет объявление.
    //     * Проверяет права доступа: удалить может только владелец или ADMIN.
    //     * @param id идентификатор удаляемого объявления

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления")
    @PreAuthorize("hasRole('ADMIN') or @adServiceImpl.isOwner(#id, principal?.username)")
    public ResponseEntity<Void> deleteAd(@PathVariable long id) {
        adService.deleteAd(id);
        return ResponseEntity.noContent().build();
    }

    //     * Безопасная конвертация любого значения в Number.
    //     * Предотвращает ClassCastException при получении строки от фронтенда.
    //     * @param value любое значение из Map (String, Number, null)
    //     * @return Number или null, если значение нечисловое

    private Number parseNumber(Object value) {
        if (value instanceof Number) {
            return (Number) value;
        }
        if (value instanceof String) {
            try {
                return Double.parseDouble((String) value); // Работает и для целых
            } catch (NumberFormatException ignored) {
                return null;
            }
        }
        return null;
    }

    // Старый дебажный метод
    // private void saveImage(long adId, MultipartFile image) {
    //     System.out.println("Saving image for ad#" + adId);
    // }
}
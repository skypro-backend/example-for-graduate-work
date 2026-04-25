package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.service.AdService;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/ads")
@Tag(name = "Объявления", description = "API для работы с объявлениями")
public class AdController {

    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @Operation(summary = "Получение всех объявлений", description = "Возвращает список всех объявлений", responses = {@ApiResponse(responseCode = "200", description = "Успешный ответ", content = @Content(schema = @Schema(implementation = Ads.class)))})
    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }

    @Operation(summary = "Добавление объявления", description = "Создаёт новое объявление с заголовком, ценой, описанием и изображением", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Форма с данными объявления и изображением", required = true, content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE, schema = @Schema(type = "object", description = "Объект, содержащий поля 'properties' и 'image'"))), responses = {@ApiResponse(responseCode = "201", description = "Объявление успешно создано", content = @Content(schema = @Schema(implementation = Ad.class))), @ApiResponse(responseCode = "401", description = "Пользователь не авторизован")})
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Ad> addAd(@RequestPart("properties") CreateOrUpdateAd properties, @RequestPart("image") byte[] image, Authentication authentication) {
        AdEntity adEntity = new AdEntity();
        adEntity.setTitle(properties.getTitle());
        adEntity.setPrice(properties.getPrice());
        adEntity.setDescription(properties.getDescription());

        Ad createdAd = adService.createAd(adEntity, authentication.getName());
        adService.updateImage(createdAd.getPk(), image);

        return ResponseEntity.status(201).body(createdAd);
    }

    @Operation(summary = "Получение информации об объявлении", description = "Возвращает полную информацию об объявлении по его ID", responses = {@ApiResponse(responseCode = "200", description = "Успешный ответ", content = @Content(schema = @Schema(implementation = ExtendedAd.class))), @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"), @ApiResponse(responseCode = "404", description = "Объявление не найдено")})
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAds(@PathVariable("id") Integer id) {
        try {
            ExtendedAd extendedAd = adService.getExtendedAd(id);
            return ResponseEntity.ok(extendedAd);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Удаление объявления", description = "Удаляет объявление по ID (только для автора или администратора)", responses = {@ApiResponse(responseCode = "204", description = "Объявление успешно удалено"), @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"), @ApiResponse(responseCode = "403", description = "Доступ запрещён"), @ApiResponse(responseCode = "404", description = "Объявление не найдено")})
    @DeleteMapping("/{id}")
    @PreAuthorize("@adService.getExtendedAd(#id).author.email == authentication.name or hasRole('ADMIN')")
    public ResponseEntity<Void> removeAd(@PathVariable("id") Integer id, Authentication authentication) {
        adService.deleteAd(id, authentication.getName());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Обновление информации об объявлении", description = "Изменяет заголовок, цену и описание объявления", responses = {@ApiResponse(responseCode = "200", description = "Объявление успешно обновлено", content = @Content(schema = @Schema(implementation = Ad.class))), @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"), @ApiResponse(responseCode = "403", description = "Доступ запрещён"), @ApiResponse(responseCode = "404", description = "Объявление не найдено")})
    @PatchMapping("/{id}")
    @PreAuthorize("@adService.getExtendedAd(#id).author.email == authentication.name or hasRole('ADMIN')")
    public ResponseEntity<Ad> updateAds(@PathVariable("id") Integer id, @RequestBody CreateOrUpdateAd createOrUpdateAd, Authentication authentication) {
        AdEntity updatedAd = new AdEntity();
        updatedAd.setTitle(createOrUpdateAd.getTitle());
        updatedAd.setPrice(createOrUpdateAd.getPrice());
        updatedAd.setDescription(createOrUpdateAd.getDescription());

        Ad result = adService.updateAd(id, updatedAd, authentication.getName());
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Получение объявлений авторизованного пользователя", description = "Возвращает список всех объявлений текущего пользователя", responses = {@ApiResponse(responseCode = "200", description = "Успешный ответ", content = @Content(schema = @Schema(implementation = Ads.class))), @ApiResponse(responseCode = "401", description = "Пользователь не авторизован")})
    @GetMapping("/me")
    public ResponseEntity<Ads> getAdsMe(Authentication authentication) {
        Ads ads = adService.getAdsByUser(authentication.getName());
        return ResponseEntity.ok(ads);
    }

    @Operation(summary = "Обновление картинки объявления", description = "Заменяет изображение объявления", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Форма с новым изображением", required = true, content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE, schema = @Schema(type = "object", description = "Объект, содержащий поле 'image'"))), responses = {@ApiResponse(responseCode = "200", description = "Изображение успешно обновлено"), @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"), @ApiResponse(responseCode = "403", description = "Доступ запрещён"), @ApiResponse(responseCode = "404", description = "Объявление не найдено")})
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("@adService.getExtendedAd(#id).author.email == authentication.name or hasRole('ADMIN')")
    public ResponseEntity<byte[]> updateImage(@PathVariable("id") Integer id, @RequestPart("image") byte[] image) {
        adService.updateImage(id, image);
        return ResponseEntity.ok(image);
    }
}
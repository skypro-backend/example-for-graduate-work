package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

import jakarta.validation.Valid;
import java.util.ArrayList;

@Slf4j
@CrossOrigin(value = "http://localhost:8080")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@Tag(name = "Объявления", description = "API для управления объявлениями")
public class AdController {

    @GetMapping
    @Operation(
            summary = "Получение всех объявлений",
            description = "Возвращает список всех объявлений"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешное получение списка",
                    content = @Content(schema = @Schema(implementation = Ads.class))
            )
    })
    public ResponseEntity<Ads> getAllAds() {
        Ads ads = new Ads();
        ads.setCount(0);
        ads.setResults(new ArrayList<>());
        return ResponseEntity.ok(ads);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Добавление объявления",
            description = "Создает новое объявление"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Объявление успешно создано",
                    content = @Content(schema = @Schema(implementation = Ad.class))
            ),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован")
    })
    public ResponseEntity<Ad> addAd(
            @RequestPart("properties") @Valid CreateOrUpdateAd properties,
            @RequestPart("image") MultipartFile image) {

        Ad ad = new Ad();
        ad.setAuthor(1);
        ad.setImage("/images/new-ad.jpg");
        ad.setPk(1);
        ad.setPrice(properties.getPrice());
        ad.setTitle(properties.getTitle());
        return ResponseEntity.status(HttpStatus.CREATED).body(ad);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение информации об объявлении",
            description = "Возвращает детальную информацию об объявлении по ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешное получение данных",
                    content = @Content(schema = @Schema(implementation = ExtendedAd.class))
            ),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"),
            @ApiResponse(responseCode = "404", description = "Объявление не найдено")
    })
    public ResponseEntity<ExtendedAd> getAds(@PathVariable Integer id) {
        ExtendedAd ad = new ExtendedAd();
        ad.setPk(id);
        ad.setAuthorFirstName("John");
        ad.setAuthorLastName("Doe");
        ad.setDescription("Пример описания объявления");
        ad.setEmail("user@example.com");
        ad.setImage("/images/ad-" + id + ".jpg");
        ad.setPhone("+7 (123) 456-78-90");
        ad.setPrice(50000);
        ad.setTitle("Пример объявления");
        return ResponseEntity.ok(ad);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление объявления",
            description = "Удаляет объявление по ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Объявление успешно удалено"),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            @ApiResponse(responseCode = "404", description = "Объявление не найдено")
    })
    public ResponseEntity<Void> removeAd(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    @Operation(
            summary = "Обновление информации об объявлении",
            description = "Обновляет данные объявления по ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Объявление успешно обновлено",
                    content = @Content(schema = @Schema(implementation = Ad.class))
            ),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            @ApiResponse(responseCode = "404", description = "Объявление не найдено")
    })
    public ResponseEntity<Ad> updateAds(
            @PathVariable Integer id,
            @Valid @RequestBody CreateOrUpdateAd createOrUpdateAd) {

        Ad ad = new Ad();
        ad.setAuthor(1);
        ad.setImage("/images/ad-" + id + ".jpg");
        ad.setPk(id);
        ad.setPrice(createOrUpdateAd.getPrice());
        ad.setTitle(createOrUpdateAd.getTitle());
        return ResponseEntity.ok(ad);
    }

    @GetMapping("/me")
    @Operation(
            summary = "Получение объявлений авторизованного пользователя",
            description = "Возвращает список объявлений текущего пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешное получение списка",
                    content = @Content(schema = @Schema(implementation = Ads.class))
            ),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован")
    })
    public ResponseEntity<Ads> getAdsMe() {
        Ads ads = new Ads();
        ads.setCount(0);
        ads.setResults(new ArrayList<>());
        return ResponseEntity.ok(ads);
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Обновление картинки объявления",
            description = "Загружает новое изображение для объявления"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Изображение успешно обновлено",
                    content = @Content(mediaType = "application/octet-stream")
            ),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            @ApiResponse(responseCode = "404", description = "Объявление не найдено")
    })
    public ResponseEntity<byte[]> updateImage(
            @PathVariable Integer id,
            @RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok(new byte[0]);
    }
}
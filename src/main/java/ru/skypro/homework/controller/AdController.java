package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ads.AdListDto;
import ru.skypro.homework.dto.Ads.AdShortDto;
import ru.skypro.homework.dto.Ads.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.Ads.ExtendedAdDto;

@RestController
@RequestMapping("/ads")
@Tag(name = "Объявления", description = "API для работы с объявлениями")
public class AdController {

    // ==================== 1. ПОЛУЧЕНИЕ ВСЕХ ОБЪЯВЛЕНИЙ ====================

    @GetMapping
    @Operation(
            summary = "Получение всех объявлений",
            description = "Возвращает пагинированный список всех объявлений"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK. Список объявлений успешно получен",
                    content = @Content(schema = @Schema(implementation = AdListDto.class))
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<AdListDto> getAllAds() {
        // Скелет: возвращаем пустой список
        return ResponseEntity.ok(new AdListDto());
    }

    // ==================== 2. ПОЛУЧЕНИЕ ОБЪЯВЛЕНИЙ АВТОРИЗОВАННОГО ПОЛЬЗОВАТЕЛЯ (НОВЫЙ) ====================

    @GetMapping("/me")
    @Operation(
            summary = "Получение объявлений авторизованного пользователя",
            description = "Возвращает список объявлений, созданных текущим авторизованным пользователем"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK. Список объявлений получен",
                    content = @Content(schema = @Schema(implementation = AdListDto.class))
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<AdListDto> getMyAds() {
        // Скелет: возвращаем пустой список
        return ResponseEntity.ok(new AdListDto());
    }

    // ==================== 3. ДОБАВЛЕНИЕ ОБЪЯВЛЕНИЯ ====================

    @PostMapping(consumes = {"multipart/form-data"})
    @Operation(
            summary = "Добавление объявления",
            description = "Создаёт новое объявление с изображением и данными из JSON поля 'properties'"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created. Объявление успешно создано",
                    content = @Content(schema = @Schema(implementation = AdShortDto.class))
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<AdShortDto> addAd(
            @Parameter(description = "Данные объявления (JSON)", required = true)
            @RequestPart("properties") CreateOrUpdateAdDto properties,

            @Parameter(description = "Изображение объявления", required = true)
            @RequestPart("image") MultipartFile image
    ) {
        // Скелет: возвращаем пустой объект и статус 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(new AdShortDto());
    }

    // ==================== 4. ПОЛУЧЕНИЕ ОБЪЯВЛЕНИЯ ПО ID ====================

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение информации об объявлении",
            description = "Возвращает расширенную информацию об объявлении по его ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK. Информация получена",
                    content = @Content(schema = @Schema(implementation = ExtendedAdDto.class))
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found. Объявление не найдено", content = @Content)
    })
    public ResponseEntity<ExtendedAdDto> getAdById(
            @Parameter(description = "ID объявления", example = "123", required = true)
            @PathVariable Long id
    ) {
        // Скелет: возвращаем пустой объект
        return ResponseEntity.ok(new ExtendedAdDto());
    }

    // ==================== 5. УДАЛЕНИЕ ОБЪЯВЛЕНИЯ ====================

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление объявления",
            description = "Удаляет объявление по ID. Доступно только автору или администратору"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content. Объявление удалено"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden. Нет прав на удаление", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found. Объявление не найдено", content = @Content)
    })
    public ResponseEntity<Void> deleteAd(
            @Parameter(description = "ID объявления", example = "123", required = true)
            @PathVariable Long id
    ) {
        // Скелет: возвращаем 204 No Content
        return ResponseEntity.noContent().build();
    }

    // ==================== 6. ОБНОВЛЕНИЕ ОБЪЯВЛЕНИЯ ====================

    @PatchMapping("/{id}")
    @Operation(
            summary = "Обновление информации об объявлении",
            description = "Обновляет заголовок, цену и описание объявления"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK. Объявление обновлено",
                    content = @Content(schema = @Schema(implementation = AdShortDto.class))
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden. Нет прав на редактирование", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found. Объявление не найдено", content = @Content)
    })
    public ResponseEntity<AdShortDto> updateAd(
            @Parameter(description = "ID объявления", example = "123", required = true)
            @PathVariable Long id,

            @Parameter(description = "Данные для обновления", required = true)
            @RequestBody CreateOrUpdateAdDto updateAd
    ) {
        // Скелет: возвращаем пустой объект
        return ResponseEntity.ok(new AdShortDto());
    }

    // ==================== 7. ОБНОВЛЕНИЕ КАРТИНКИ ОБЪЯВЛЕНИЯ (НОВЫЙ) ====================

    @PatchMapping(value = "/{id}/image", consumes = {"multipart/form-data"})
    @Operation(
            summary = "Обновление картинки объявления",
            description = "Заменяет изображение объявления на новое"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. Картинка обновлена"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden. Нет прав на редактирование", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found. Объявление не найдено", content = @Content)
    })
    public ResponseEntity<Void> updateAdImage(
            @Parameter(description = "ID объявления", example = "123", required = true)
            @PathVariable Long id,

            @Parameter(description = "Новое изображение", required = true)
            @RequestParam("image") MultipartFile image
    ) {
        // Скелет: проверяем, что файл передан
        if (image == null || image.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        System.out.println("Обновление картинки для объявления ID: " + id);
        System.out.println("Файл: " + image.getOriginalFilename());

        // Скелет: возвращаем 200 OK
        return ResponseEntity.ok().build();
    }
}
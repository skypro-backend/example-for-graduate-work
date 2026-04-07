package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.entity.AdvertisementEntity;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.service.AdService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
@Validated
@Tag(name = "Объявления", description = "Управление объявлениями")
public class AdvertisementController {
    private final AdService adService;
    private final AdMapper adMapper;
    @Operation(summary = "Получение всех объявлений", operationId = "getAllAds")
    @ApiResponse(responseCode = "200", description = "Успешное получение списка объявлений",
            content = @Content(schema = @Schema(implementation = Ad.class)))
    @GetMapping
    public ResponseEntity<List<Ad>> getAllAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }

    /**
    POST /ads — Создание объявления
    */

    @Operation(summary = "Создание объявления", operationId = "createAd")
    @ApiResponse(responseCode = "201", description = "Объявление создано",
            content = @Content(schema = @Schema(implementation = Ad.class)))
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Ad> createAd(
            @RequestPart("properties") @Valid CreateOrUpdateAd properties,
            @RequestPart("image") MultipartFile image,
            Authentication authentication
    ) {
        Ad newAd = adService.createAd(properties, image, authentication.getName());
        return ResponseEntity.status(201).body(newAd);
    }

    @Operation(summary = "Получение объявления по ID", operationId = "getAdById")
    @ApiResponse(responseCode = "200", description = "Успешное получение объявления",
            content = @Content(schema = @Schema(implementation = Ad.class)))
    @ApiResponse(responseCode = "404", description = "Объявление не найдено")
    @GetMapping("/{id}")
    public ResponseEntity<Ad> getAdById(@PathVariable Long id) {
        Ad ad = adService.getAdById(id);
        return ResponseEntity.ok(ad);
    }

    /**
    DELETE /ads/{id} — Удаление объявления
     */
    @Operation(summary = "Удаление объявления по ID", operationId = "deleteAd")
    @ApiResponse(responseCode = "204", description = "Объявление удалено")
    @ApiResponse(responseCode = "404", description = "Объявление не найдено")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAd(
            @Parameter(description = "ID объявления", required = true, example = "1")
            @PathVariable Long id) {
        adService.deleteAd(id);
        return ResponseEntity.noContent().build(); // HTTP 204
    }

    /**
    PATCH /ads/{id} — Обновление информации об объявлении
     */
    @Operation(summary = "Обновление объявления по ID", operationId = "updateAd")
    @ApiResponse(responseCode = "200", description = "Объявление обновлено",
            content = @Content(schema = @Schema(implementation = Ad.class)))
    @ApiResponse(responseCode = "404", description = "Объявление не найдено")
    @PatchMapping("/{id}")
    public ResponseEntity<Ad> updateAd(
            @Parameter(description = "ID объявления", required = true, example = "1")
            @PathVariable Long id,
            @Parameter(description = "Данные для обновления", required = true)
            @Valid @RequestBody CreateOrUpdateAd request) {

        return ResponseEntity.ok(adMapper.toDto(adService.updateAd(id, request)));
    }

    @Operation(summary = "Получение объявлений текущего пользователя", operationId = "getMyAds")
    @ApiResponse(responseCode = "200", description = "Успешное получение списка объявлений",
            content = @Content(schema = @Schema(implementation = Ad.class)))
    @GetMapping("/me")
    public ResponseEntity<List<Ad>> getMyAds(@AuthenticationPrincipal UserDetails userDetails) {
        List<Ad> ads = adService.getMyAds(userDetails.getUsername())
                .stream()
                .map(adMapper::toDto)
                .toList();

        return ResponseEntity.ok(ads);
    }
    /**
     * PATCH /ads/{id}/image - Обновление картинки объявления
     */

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление картинки объявления", operationId = "updateImage")
    @ApiResponse(responseCode = "200", description = "Картинка обновлена")
    @ApiResponse(responseCode = "404", description = "Объявление не найдено")
    public ResponseEntity<Ad> updateImage(
            @PathVariable Long id,
            @RequestParam("image") MultipartFile file) {

        AdvertisementEntity updatedAd = adService.updateAdImage(id, file);
        return ResponseEntity.ok(adMapper.toDto(updatedAd));
    }
}
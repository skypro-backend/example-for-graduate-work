package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@Tag(name = "Объявления")
public class AdsController {
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления",
            description = "Удаление объявления по идентификационному номеру авторизованным пользователем")
    @ApiResponse(responseCode = "204", description = "No Content")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not Found")
    public ResponseEntity<Void> removeAd(@PathVariable("id") Integer id) {
        // Реализация удаления объявления
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Получение всех объявлений",
            description = "Получение количества и списка всех объявлений")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "204", description = "No Content")
    @ApiResponse(responseCode = "404", description = "Not Found")
    public ResponseEntity<AdsDto> getAllAds() {
        AdsDto AdsDtoList = new AdsDto(0, new ArrayList<>());
        return ResponseEntity.ok(AdsDtoList);
    }

    @SneakyThrows
    @Operation(summary = "Добавление объявления",
            description = "Добавление изображения и всех полей объявления")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Not found")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdsDto> addAds(@Parameter(description = "Данные нового объявления")
                                         @RequestPart("image") MultipartFile imageFile,
                                         @Valid @RequestPart("properties") AdsDto createAdsDto, Authentication authentication) {
        return ResponseEntity.ok(createAdsDto);
    }

    @Operation(summary = "Получение информации об объявлении",
            description = "Получение информации об объявлении по его id")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Not found")
    @GetMapping("/{adId}")
    public ResponseEntity<AdsDto> getAuthById(@PathVariable("adId") Integer adId,
                                              @RequestBody AdsDto adsDto) {
        return ResponseEntity.ok(adsDto);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновление информации об объявлении",
            description = "Обновление информации об объявлении по id объявления")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<AdDto> updateAd(@PathVariable("id") Integer id,
                                          @RequestBody AdDto updatedAdDto) {
        return ResponseEntity.ok(updatedAdDto);
    }

    @GetMapping("/me")
    @Operation(summary = "Получение объявлений авторизованного пользователя")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<AdsDto> getAuthorizedUserAds() {
        AdsDto list = new AdsDto(0, new ArrayList<>());
        return ResponseEntity.ok(list);
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление картинки объявления",
            description = "Обновление картинки объявления по id объявления")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<String> updateAdImage(@PathVariable("id") Integer id,
                                                @RequestParam MultipartFile image) throws IOException {
        String result = "Успешно";
        return ResponseEntity.ok(result);
    }

}




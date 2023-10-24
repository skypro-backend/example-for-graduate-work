package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;

import java.io.IOException;
import java.util.ArrayList;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@Tag(name = "Объявления")
@Validated
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
    public ResponseEntity<AdsDto> getAllAds() {
        AdsDto allAdsDtoList = new AdsDto(0, new ArrayList<>());
        return ResponseEntity.ok(allAdsDtoList);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Добавление объявления",
            description = "Добавление изображения и всех полей объявления")
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<AdDto> addAd(@RequestPart("properties") CreateOrUpdateAdDto createOrUpdateAdDto,
                                       @RequestPart MultipartFile image) throws IOException {
        AdDto addedAdDto = new AdDto(1, "imagePath", 1, createOrUpdateAdDto.price(), createOrUpdateAdDto.title());
        return ResponseEntity.ok(addedAdDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об объявлении",
            description = "Получение информации об объявлении по его id")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<ExtendedAdDto> getAuthById(@PathVariable("id") Integer id) {
        ExtendedAdDto extendedAdDto = new ExtendedAdDto(id, "authorFirstName",
                "authorLastName", "description", "email", "imagePath",
                "phone", 100, "title");
        return ResponseEntity.ok(extendedAdDto);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновление информации об объявлении",
            description = "Обновление информации об объявлении по id объявления")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<AdDto> updateAd(@PathVariable("id") Integer id,
                                          @RequestBody CreateOrUpdateAdDto createOrUpdateAdDto) {
        AdDto updatedAdDto = new AdDto(1, "imagePath",
                id, createOrUpdateAdDto.price(), createOrUpdateAdDto.title());
        return ResponseEntity.ok(updatedAdDto);
    }

    @GetMapping("/me")
    @Operation(summary = "Получение объявлений авторизованного пользователя")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<AdsDto> getAuthorizedUserAds() {
        AdsDto userAdsDtoList = new AdsDto(0, new ArrayList<>());
        return ResponseEntity.ok(userAdsDtoList);
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
        return ResponseEntity.ok("Image updated successfully");
    }

}




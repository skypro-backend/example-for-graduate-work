package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.service.AdService;

import javax.validation.Valid;
import java.io.IOException;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@Tag(name = "Объявления")
@Validated
@RequiredArgsConstructor

public class AdsController {
    private final AdService adService;

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления",
            description = "Удаление объявления по идентификационному номеру авторизованным пользователем")
    @ApiResponse(responseCode = "204", description = "No Content")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not Found")
    public ResponseEntity<Void> removeAd(@PathVariable("id") Integer id, Authentication authentication) {
        adService.removeAd(id, authentication);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Получение всех объявлений",
            description = "Получение количества и списка всех объявлений")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<AdsDto> getAllAds() {
        AdsDto allAdsDtoList = adService.getAllAds();
        return ResponseEntity.ok(allAdsDtoList);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Добавление объявления",
            description = "Добавление изображения и всех полей объявления")
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<AdDto> addAd(@RequestPart("properties") @Valid CreateOrUpdateAdDto createOrUpdateAdDto,
                                       @RequestPart MultipartFile image, Authentication authentication) throws IOException {
        AdDto addedAdDto = adService.addAd(createOrUpdateAdDto, image, authentication);
        return ResponseEntity.ok(addedAdDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об объявлении",
            description = "Получение информации об объявлении по его id")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<ExtendedAdDto> getExtendedAdById(@PathVariable("id") Integer id) {
        ExtendedAdDto extendedAdDto = adService.getAdById(id);
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
                                          @RequestBody @Valid CreateOrUpdateAdDto createOrUpdateAdDto) {
        return ResponseEntity.ok(adService.updateAd(id, createOrUpdateAdDto));
    }

    @GetMapping("/me")
    @Operation(summary = "Получение объявлений авторизованного пользователя")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<AdsDto> getAuthorizedUserAds() {
        AdsDto userAdsDtoList = adService.getAuthorizedUserAds();
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
        adService.updateAdImage(id, image);
        return ResponseEntity.ok("Image updated successfully");
    }

}




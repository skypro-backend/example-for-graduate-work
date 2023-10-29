package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.model_dto.AdDto;
import ru.skypro.homework.dto.model_dto.AdsDto;
import ru.skypro.homework.dto.model_dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.model_dto.ExtendedAdDto;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * Контроллер для работы с объявлениями
 */
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@Tag (name = "Объявления", description = "работа с объявлениями")
public class AdsController {

    private final AdService adService;
    private final ImageService imageService;

    @Operation(summary = "Получение всех объявлений", responses = {
              @ApiResponse (responseCode = "200", description = "OK", content = {
                        @Content (mediaType = "application/json", array = @ArraySchema (schema =
                        @Schema (implementation = AdsDto.class)))})
    })
    @GetMapping
    public ResponseEntity<AdsDto> getAllAds() {
        return ResponseEntity.ok((AdsDto) adService.getAllAds ());
    }


    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE , MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "Добавление (создание) объявления", responses = {
              @ApiResponse(responseCode = "201", description = "Created", content = {
                        @Content(mediaType = "application/json", array = @ArraySchema(schema =
                        @Schema(implementation = AdDto.class)))}),
              @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<AdDto> addAd(@RequestPart("properties") @Valid CreateOrUpdateAdDto createOrUpdateAdDto,
                                       @RequestPart("image") @Valid MultipartFile image) {
            return ResponseEntity.ok (adService.addAd (createOrUpdateAdDto, image));
    }


    @Operation(summary = "Получение информации об объявлении", responses = {
              @ApiResponse(responseCode = "200", description = "OK", content = {
                        @Content(mediaType = "application/json", array = @ArraySchema(schema =
                        @Schema(implementation = ExtendedAdDto.class)))}),
              @ApiResponse(responseCode = "401", description = "Unauthorized"),
              @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDto> getAdInformation(@PathVariable("id") Integer id) {
            return ResponseEntity.ok(adService.getAdInformation (id));
    }


    @Operation(summary = "Удаление объявления", responses = {
              @ApiResponse(responseCode = "204", description = "No Content"),
              @ApiResponse(responseCode = "401", description = "Unauthorized"),
              @ApiResponse(responseCode = "403", description = "Forbidden"),
              @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable("id") Integer id, Authentication authentication) {
        adService.deleteAd (id, authentication);
            return ResponseEntity.noContent().build();
        }


    @Operation(summary = "Обновление информации об объявлении", responses = {
              @ApiResponse(responseCode = "200", description = "OK", content = {
                        @Content(mediaType = "application/json", array = @ArraySchema(schema =
                        @Schema(implementation = AdDto.class)))}),
              @ApiResponse(responseCode = "401", description = "Unauthorized"),
              @ApiResponse(responseCode = "403", description = "Forbidden"),
              @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<AdDto> updateAdInformation(@PathVariable("id") Integer id,
                                                     @RequestBody CreateOrUpdateAdDto createOrUpdateAdDto,
                                                     Authentication authentication) {
            return ResponseEntity.ok(adService.updateAdInformation (id, createOrUpdateAdDto, authentication));
    }


    @Operation(summary = "Получение объявлений авторизованного пользователя", responses = {
              @ApiResponse(responseCode = "200", description = "OK", content = {
                        @Content(mediaType = "application/json", array = @ArraySchema(schema =
                        @Schema(implementation = AdsDto.class)))}),
              @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/me")
    public ResponseEntity<AdDto> getAuthorizedUserAds(Authentication authentication) {
        return ResponseEntity.ok((AdDto) adService.getAuthorizedUserAds(authentication));
    }


    @Operation(summary = "Обновление картинки объявления", responses = {
              @ApiResponse(responseCode = "200", description = "OK", content = {
                        @Content(mediaType = "application/octet-stream", array = @ArraySchema(schema =
                        @Schema(implementation = String.class)))}),
              @ApiResponse(responseCode = "401", description = "Unauthorized"),
              @ApiResponse(responseCode = "403", description = "Forbidden"),
              @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateAdImage(@PathVariable("id") Integer adsId,
                                                @RequestPart("image") @Valid MultipartFile imageFile,
                                                Authentication authentication) throws Exception {
        adService.updateImageAd (adsId, imageFile, authentication);
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "getAdsImage", description = "Запрос на получение картинки объявления",
              tags = {"Объявления"},
              responses = {
                        @ApiResponse(responseCode = "200", description = "Ok",
                                  content = @Content(
                                            mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE)),
                        @ApiResponse(responseCode = "404", description = "Not found")
              })
    @GetMapping(value = "/me/image/{imageId}")
    public ResponseEntity<byte[]> getAdsImage(@PathVariable Integer imageId) throws IOException {
        imageService.getImage (imageId);
        return ResponseEntity.ok().build();
    }
}

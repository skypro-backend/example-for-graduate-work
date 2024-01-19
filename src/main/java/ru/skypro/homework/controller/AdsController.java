package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.impl.AdsServiceImpl;
import ru.skypro.homework.service.impl.ImageServiceImpl;
import java.io.IOException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")

public class AdsController {
    private final AdsServiceImpl adService;
    private final ImageServiceImpl imageService;

    @GetMapping()
    @Operation(summary = "Получение всех объявлений", description = "getAllAds")
    public ResponseEntity<AdsDto> getAllAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE) //+ 401 Unauthorized
    @Operation(summary = "Добавление объявления", description = "addAd")
    public ResponseEntity<AdDto> addAd(@RequestPart(value = "properties", required = false) CreateOrUpdateAd createOrUpdateAdDTO,
                                       @RequestPart("image") MultipartFile image,
                                       Authentication authentication) {
        return ResponseEntity.ok(adService.addAd(createOrUpdateAdDTO, image, authentication));
    }

    @GetMapping("{id}") //+ 401 Unauthorized + 404 Not found
    @Operation(summary = "Получение информации об объявлении", description = "getAd")
    public ResponseEntity<ExtendedAd> getAd(@PathVariable long id){
        return ResponseEntity.ok(adService.getAd(id));
    }

    @DeleteMapping("{id}") //+ 401 Unauthorized + 403 Forbidden + 404 Not found
    @Operation(summary = "Удаление объявления", description = "removeAd")
    public ResponseEntity<?> deleteAd(@Parameter(description = "ID объявления")
                                      @PathVariable long id,
                                      Authentication authentication){
        adService.deleteAd(id,authentication);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("{id}") //+ 401 Unauthorized + 403 Forbidden + 404 Not found
    @Operation(summary = "Обновление информации об объявлении", description = "updateInfoAd")
    public ResponseEntity<AdDto> updateAdInformation(@Parameter(description = "ID объявления") @PathVariable long id,
                                                     @RequestBody CreateOrUpdateAd createOrUpdateAd,
                                                     Authentication authentication){
        return ResponseEntity.ok(adService.updateAd(id, createOrUpdateAd,authentication));
    }

    @GetMapping("/me") //+ 401 Unauthorized
    @Operation(summary = "Получение объявлений авторизованного пользователя", description = "getAdsMe")
    public ResponseEntity<AdsDto> getAdsFromAuthorizedUser(Authentication authentication) {
        return ResponseEntity.ok(adService.getAdsMe(authentication));
    }

    @PatchMapping(value = "{id}/image" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE) //+ 401 Unauthorized + 403 Forbidden + 404 Not found
    @Operation(summary = "Обновление картинки объявления", description = "updateImage")
    public ResponseEntity<?> updateImage(@Parameter(description = "ID объявления") @PathVariable("id") long id,
                                         @RequestPart("image") MultipartFile image, Authentication authentication){
        adService.updateAdImage(id,image,authentication);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/image/{id}", produces = {MediaType.IMAGE_PNG_VALUE,
            MediaType.IMAGE_JPEG_VALUE,
            MediaType.IMAGE_GIF_VALUE
    })
    @Operation(summary = "Получение картинки объявления", description = "getAdsImage")
    public ResponseEntity<byte[]> getAdsImage(@PathVariable("id") long id) {
        byte[] image = imageService.getImage(id).getData();
        return ResponseEntity.ok(image);
    }
}
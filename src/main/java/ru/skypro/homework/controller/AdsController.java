package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.dto.ad.AdsDto;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ad.ExtendedAdDto;
import ru.skypro.homework.service.AdService;

import java.io.IOException;

@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
@Tag(name = "объявления")
public class AdsController {

    private final AdService adService;

    @GetMapping
    @Operation(summary = "Получение всех объявлений")
    public ResponseEntity<AdsDto> getAllAds(){
        return ResponseEntity.ok(adService.getAllAds());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Добавление объявления")
    public ResponseEntity<AdDto> addAd(@RequestPart("properties") String propertiesJson,
                                       @RequestPart("image")MultipartFile image,
                                       Authentication authentication) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CreateOrUpdateAdDto properties = mapper.readValue(propertiesJson, CreateOrUpdateAdDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(adService.addAd(authentication.getName(),
                properties, image));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об объявлении")
    public ResponseEntity<ExtendedAdDto> getAds(@PathVariable Integer id){
        return ResponseEntity.ok(adService.getAd(id));
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления")
    public ResponseEntity<Void> removeAd(@PathVariable Integer id,
                                         Authentication authentication){
        adService.removeAd(authentication.getName(), id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновление информации об объявлении")
    public ResponseEntity<AdDto> updateAds(@PathVariable Integer id,
                                           @RequestBody CreateOrUpdateAdDto dto,
                                           Authentication authentication){
        return ResponseEntity.ok(adService.updateAd(authentication.getName(), id, dto));
    }

    @GetMapping("/me")
    @Operation(summary = "Получение объявлений авторизированного пользователя")
    public ResponseEntity<AdsDto> getAdsMe(Authentication authentication){
        return ResponseEntity.ok(adService.getAdsMe(authentication.getName()));
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление картинки объявления")
    public ResponseEntity<byte[]> updateImage(@PathVariable Integer id,
                                              @RequestPart("image") MultipartFile image,
                                              Authentication authentication){
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(adService.updateAdImage(authentication.getName(), id, image));
    }
}

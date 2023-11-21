package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.PhotoEntity;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.impl.AuthServiceImpl;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ads")
public class AdController {
    AdService adService;
    AuthServiceImpl authService;

    public AdController(AdService adService, AuthService authService) {
        this.adService = adService;
        this.authService = (AuthServiceImpl) authService;
    }

    @GetMapping
    public ResponseEntity<Ads> getAllAds() {

        return ResponseEntity.ok(adService.getAllAds());
    }

    @PostMapping
    public ResponseEntity<Ad> addAd(@RequestParam CreateOrUpdateAd properties, @RequestParam MultipartFile image) throws IOException {
        Ad ad = adService.addAd(properties, image); // метод в разработке
        return ResponseEntity.ok(ad);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAds(@PathVariable("id") Integer id) {

        ExtendedAd ad = adService.getAds(id);
        if (ad != null) {
            return ResponseEntity.ok(ad);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeAd(@PathVariable("id") Integer id) {

        return (adService.removeAd(id)) ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Ad> updateAds(@PathVariable("id") Integer id, @RequestBody CreateOrUpdateAd dto) {

        Ad ad = adService.updateAds(id, dto);
        if (ad != null) {
            return ResponseEntity.ok(ad);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
    }

    @GetMapping("/me")
    public ResponseEntity<Ads> getAdsMe() {

        if (authService.getLogin() != null) {   //если пользователь авторизовался

            String username = authService.getLogin().getUsername();
            return ResponseEntity.ok(adService.getAdsMe(username));

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "Переданный файл слишком большой",
                            content = @Content(
                            )
                    )
            }
    )
    public ResponseEntity<PhotoEntity> updateImage(@PathVariable("id") Integer id,
                                                   @RequestParam MultipartFile image) throws IOException {
        if (image.getSize() > 1024 * 1024 * 2) {
            return ResponseEntity.status(HttpStatus.valueOf(404)).build();
        }
        if (authService.getLogin() != null) {
            PhotoEntity photo = adService.updateImage(id, image);
            return ResponseEntity.ok(photo); // todo продумать, что метод возвращает, как вариант PhotoEntity
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        //return null; //todo разобраться с ошибками 403 и 404, как и в остальных методах выше, если есть
    }
}
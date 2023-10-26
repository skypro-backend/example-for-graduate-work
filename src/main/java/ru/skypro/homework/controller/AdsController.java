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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.exceptions.AdNotFoundException;
import ru.skypro.homework.service.AdService;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@Validated
@RequestMapping("/ads")
@RequiredArgsConstructor
@Tag(name = "Объявления", description = "Добавление, получение, обновление и удаление объявлений")
public class AdsController {

    private final AdService adService;

    @GetMapping("")
    @Operation(summary = "Получение всех объявлений",
            description = "Получение всех объявлений любым пользователем.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все объявления получены", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Ads.class))})
    })
    public ResponseEntity<Ads> getAllAds() {
        Ads ads = adService.getAllAds();
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Добавление объявления", description = "Добавление объявления авторизованным пользователем")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Объявление добавлено", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Ad.class)) }),
            @ApiResponse(responseCode = "401", description = "Требуется авторизация")
    })
    public ResponseEntity<Ad> postAd(@RequestPart("properties") CreateOrUpdateAd properties,
                                     @RequestPart("image") MultipartFile file, Authentication authentication) {
        Ad ad = adService.postAd(properties, file, authentication.getName());
        return new ResponseEntity<>(ad, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об объявлении",
            description = "Получение информации об объявлении по идентификационному номеру " +
                    "авторизованным пользователем")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информация получена", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ExtendedAd.class))}),
            @ApiResponse(responseCode = "401", description = "Требуется авторизация"),
            @ApiResponse(responseCode = "404", description = "Объявление не найдено")
    })
    public ResponseEntity<ExtendedAd> getAdById(@PathVariable int id,
                                                Authentication authentication) throws AdNotFoundException {
        ExtendedAd extendedAd = adService.getAdById(id);
        return new ResponseEntity<>(extendedAd, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления",
            description = "Удаление объявления по идентификационному номеру авторизованным пользователем")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Объявление удалено"),
            @ApiResponse(responseCode = "401", description = "Требуется авторизация"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            @ApiResponse(responseCode = "404", description = "Объявление не найдено")
    })
    public ResponseEntity<Void> deleteAdById(@PathVariable int id, Authentication authentication) {
        adService.deleteAdById(id, authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновление информации об объявлении",
            description = "Обновление информации об объявлении по идентификационному номеру авторизованным пользователем")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Объявление обновлено", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Ad.class)) }),
            @ApiResponse(responseCode = "401", description = "Требуется авторизация"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            @ApiResponse(responseCode = "404", description = "Объявление не найдено")
    })
    public ResponseEntity<Ad> patchAdById(@PathVariable int id,
                                          @Valid @RequestBody CreateOrUpdateAd createOrUpdateAd,
                                          Authentication authentication) throws AdNotFoundException {
        Ad ad = adService.patchAdById(id, createOrUpdateAd, authentication.getName());
        return new ResponseEntity<>(ad, HttpStatus.OK);
    }

    @GetMapping("/me")
    @Operation(summary = "Получение объявлений авторизованного пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Объявления получены", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Ads.class)) }),
            @ApiResponse(responseCode = "401", description = "Требуется авторизация")
    })
    public ResponseEntity<Ads> getMyAds(Authentication authentication) {
        Ads myAds = adService.getMyAds(authentication.getName());
        return new ResponseEntity<>(myAds, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление картинки объявления",
            description = "Обновление картинки объявлении по идентификационному номеру авторизованным пользователем")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Картинка обновлена", content = {
                    @Content(mediaType = "application/octet-stream")}),
            @ApiResponse(responseCode = "401", description = "Требуется авторизация"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            @ApiResponse(responseCode = "404", description = "Объявление не найдено")
    })
    public ResponseEntity<byte[]> patchAdsImageById(@PathVariable int id,
                                                    @RequestParam("image") MultipartFile file,
                                                    Authentication authentication) {
        byte[] image = adService.patchAdsImageById(id, file, authentication.getName());
        return new ResponseEntity<>(image, HttpStatus.OK);
    }
}

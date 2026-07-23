package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.service.AdService;

import javax.validation.Valid;

@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
@Tag(name = "Объявления")
public class AdsController {

    private final AdService adService;

    @Operation(
            summary = "Получение всех объявлений"
    )
    @GetMapping
    public ResponseEntity<Ads> getAllAds() {

        return ResponseEntity.ok(
                adService.getAllAds()
        );
    }

    @Operation(
            summary = "Добавление объявления"
    )
    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Ad> addAd(
            @Valid @RequestPart("properties") CreateOrUpdateAd properties,
            @RequestPart("image") MultipartFile image
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        adService.createAd(
                                properties,
                                image
                        )
                );
    }

    @Operation(
            summary = "Получение информации об объявлении"
    )
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAd(
            @PathVariable Integer id
    ) {


        return ResponseEntity.ok(
                adService.getAd(id)
        );
    }

    @Operation(
            summary = "Удаление объявления"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAd(
            @PathVariable Integer id
    ) {


        adService.deleteAd(id);


        return ResponseEntity.noContent()
                .build();
    }

    @Operation(
            summary = "Обновление объявления"
    )
    @PatchMapping("/{id}")
    public ResponseEntity<Ad> updateAd(
            @PathVariable Integer id,
            @Valid @RequestBody CreateOrUpdateAd ad
    ) {


        return ResponseEntity.ok(
                adService.updateAd(
                        id,
                        ad
                )
        );
    }

    @Operation(
            summary = "Получение объявлений пользователя"
    )
    @GetMapping("/me")
    public ResponseEntity<Ads> getMyAds() {


        return ResponseEntity.ok(
                adService.getMyAds()
        );
    }

    @Operation(
            summary = "Обновление картинки объявления"
    )
    @PatchMapping(
            value = "/{id}/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Void> updateImage(
            @PathVariable Integer id,
            @RequestPart("image") MultipartFile image
    ) {


        adService.updateImage(
                id,
                image
        );

        return ResponseEntity.ok()
                .build();
    }
}
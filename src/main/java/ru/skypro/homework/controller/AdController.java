package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.service.AdService;

import java.io.IOException;


@CrossOrigin(value = "http://localhost:3000")
@Slf4j
@RestController
@Validated
@RequestMapping("/ads")
public class AdController {

    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @Operation(summary = "Добавить объявление", tags = "Объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdDTO.class)
                    )}),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdDTO> addAds(@RequestPart("image") MultipartFile imageFile,
                                        Authentication authentication,
                                        @RequestPart("properties") CreateOrUpdateAd createOrUpdateAd) throws IOException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(adService.createAd(createOrUpdateAd, authentication, imageFile));
    }

    @Operation(summary = "Получить все объявления", tags = "Объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDTO.class)
                    )})
    })
    @GetMapping
    public ResponseEntity<AdsDTO> all() {
        return ResponseEntity.ok(adService.getAll());
    }

    @Operation(summary = "Получить информацию об объявлении", tags = "Объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExtendedAdDTO.class)
                    )}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content})
    })
    @GetMapping("{id}")
    public ResponseEntity<ExtendedAdDTO> findAdById(@PathVariable int id) {
        return ResponseEntity.ok(adService.findAd(id));
    }

    @Operation(summary = "Удалить объявление", tags = "Объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content", content = {@Content()}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content()}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {@Content()})
    })
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAd(@PathVariable int id, Authentication authentication) throws IOException {
        if (adService.deleteAd(id, authentication)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Operation(summary = "Обновить информацию об объявлении", tags = "Объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdDTO.class)
                    )}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content()}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {@Content()})
    })
    @PatchMapping("{id}")
    public ResponseEntity<AdDTO> updateById(@PathVariable int id,
                                            @RequestBody CreateOrUpdateAd createOrUpdateAd,
                                            Authentication authentication) {
        return ResponseEntity.ok(adService.updateAd(id, createOrUpdateAd, authentication));
    }

    @Operation(summary = "Получить объявления авторизованного пользователя", tags = "Объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDTO.class)
                    )}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content()})
    })
    @GetMapping("/me")
    public ResponseEntity<AdsDTO> getAdsMe(Authentication authentication) {
        return ResponseEntity.ok(adService.getAdsMe(authentication));
    }

    @Operation(summary = "Обновить картинку объявления", tags = "Объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE)}),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUserImage(@PathVariable int id,
                                             @RequestParam MultipartFile image,
                                             Authentication authentication) throws IOException {
        adService.editAdImage(id, image, authentication);
        return ResponseEntity.ok().build();
    }
}

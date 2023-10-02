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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdsController {
    private final AdsService adsService;
    private final ImageService imageService;

    public AdsController(AdsService adsService, ImageService imageService) {
        this.adsService = adsService;
        this.imageService = imageService;
    }

    @Operation(
            summary = "Получить все объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDto.class)
                            )
                    )
            },
            tags = "Объявления"
    )
    @GetMapping
    public ResponseEntity<AdsDto> ads() {
        AdsDto adsDto = adsService.getAllAds();
        if (adsDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(adsDto);
    }

    @Operation(
            summary = "AddAd",
            description = "Добавление объявления",
            tags = {"Объявления"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")})
    @PreAuthorize("isAuthenticated()")
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public AdDto createAd(@RequestPart(name = "properties") CreateOrUpdateAdDto createOrUpdateAdDto,
                          @RequestPart("image") MultipartFile image) {
        AdDto adDto = adsService.createAds(createOrUpdateAdDto, image);
        return adDto;
    }

        @Operation(
                summary = "getAd",
                description = "return info about ad",
                tags = {"Объявления"})
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200",
                        description = "OK",
                        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = FullAdDto.class))),
                @ApiResponse(responseCode = "401",
                        description = "Unauthorized"),
                @ApiResponse(responseCode = "404",
                        description = "Not Found")})
        @PreAuthorize("isAuthenticated()")
        @GetMapping(value = "{id}")
        public ResponseEntity<FullAdDto> getAdDto(
                @PathVariable Integer id) {
            log.info("Was invoked get full ad by id = {} method", id);
            FullAdDto adDto = adsService.getFullAdById(id);
            if (adDto == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            return ResponseEntity.ok(adDto);
        }

    @Operation(
            summary = "Удалить объявление",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "No Content"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    )
            },
            tags = "Объявления")
    @PreAuthorize("isAuthenticated()")
        @DeleteMapping("{id}")
        public ResponseEntity<Void> deleteAd (@PathVariable Integer id, Authentication authentication) {
        adsService.removeAd(id, authentication.getName());
        return ResponseEntity.ok().build();
        }

    @Operation(
            summary = "Обновить информацию об объявлении",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    )
            },
            tags = "Объявления"
    )
    @PreAuthorize("isAuthenticated()")
        @PatchMapping("{id}")
        public ResponseEntity<?> UpdateAd (@RequestBody CreateOrUpdateAdDto createOrUpdateAdDto,
                                           @PathVariable Integer id, Authentication authentication) {

        return ResponseEntity.ok().body(adsService.updateAdById(id, createOrUpdateAdDto, authentication.getName()));
        }

    @Operation(
            summary = "Получить объявления авторизованного пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            },
            tags = "Объявления"
    )
    @PreAuthorize("isAuthenticated()")
        @GetMapping("/me")
        public ResponseEntity<?> myAds (Authentication authentication) {
        AdsDto adsDto = adsService.getAllAdsForUser(authentication.getName());
        if (adsDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(adsDto);
        }

    @Operation(
            summary = "Обновить картинку объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE,
                                    schema = @Schema(implementation = String[].class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    )
            },
            tags = "Объявления"
    )
    @PreAuthorize("isAuthenticated()")
        @PatchMapping(value ="{id}/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
        public ResponseEntity<?> UpdateImage (@RequestPart MultipartFile image, @PathVariable Integer id) {

        return ResponseEntity.ok().body(adsService.updateImageById(id, image));
        }

    @GetMapping(value = "/image/{id}", produces = {
            MediaType.IMAGE_PNG_VALUE,
            MediaType.IMAGE_JPEG_VALUE,
            MediaType.APPLICATION_OCTET_STREAM_VALUE
    })
    @Operation(
            summary = "Получить картинку объявления",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content())
            })
    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id) {
        return ResponseEntity.ok(imageService.getImage(id));

    }
    }

package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.util.List;

@RequestMapping("/ads")
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Объявления")
@RestController
public class AdsController {
    private final AdsService adsService;
    private final ImageService imageService;
    private final AdMapper adMapper;

    public AdsController(AdsService adsService,ImageService imageService,AdMapper adMapper) {
        this.adsService = adsService;
        this.imageService = imageService;
        this.adMapper = adMapper;

    }

    @Operation(
            tags = "Объявления",
            summary = "Получение всех объявлений",
            responses = {
                    @ApiResponse(responseCode = "200",description = "OK",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Ads.class)))
            }
    )
    @GetMapping
    public ResponseEntity<List<?>> getAllAds(){
        return ResponseEntity.ok().build();
    }
    @Operation(
            tags = "Объявления",
            summary = "Добавление объявления",
            responses = {
                    @ApiResponse(responseCode = "201",description = "Created",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Ad.class))),
                    @ApiResponse(responseCode = "401",description = "Unauthorized",
                    content = {@Content()})
                            }
                    )


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity<CreateOrUpdateAd> addAd
            (@RequestPart  CreateOrUpdateAd properties, @RequestPart MultipartFile image, Authentication authentication) throws IOException {

        return ResponseEntity.ok(adsService.addAds(properties,image,authentication));
    }
    @Operation(
            tags = "Объявления",
            summary = "Получение информации об объявлении",
            responses = {
                    @ApiResponse(responseCode = "200",description = "OK",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ExtendedAd.class))),
                    @ApiResponse(responseCode = "401",description = "Unauthorized",
                            content = {@Content()}),
                    @ApiResponse(responseCode = "404",description = " Not found",
                    content = {@Content()})

            }
    )

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAds(@PathVariable Long id){
        return ResponseEntity.ok(adsService.getAds(id));
    }
    @Operation(
            tags = "Объявления",
            summary = "Удаление объявления",
            responses = {
                    @ApiResponse(responseCode = "204",description = "No content"),
                    @ApiResponse(responseCode = "401",description = "Unauthorized"),
                    @ApiResponse(responseCode = "403",description = "Forbidden"),
                    @ApiResponse(responseCode = "404",description = " Not found")


            }
    )

    @DeleteMapping("{id}")
    public ResponseEntity<Void> removeAd(@PathVariable int id){
        return ResponseEntity.noContent().build();
    }
    @Operation(
            tags = "Объявления",
            summary = "Обновление информации об объявлении",
            requestBody = @RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CreateOrUpdateAd.class)
                    )
            ),

            responses = {
                    @ApiResponse(responseCode = "200",description = "OK",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Ad.class))),
                    @ApiResponse(responseCode = "403",description = "Forbidden",
                    content = {@Content()}),
                    @ApiResponse(responseCode = "401",description = "Unauthorized",
                            content = {@Content()}),
                    @ApiResponse(responseCode = "404",description = "Not found",
                            content = {@Content()})

            }
    )
    @PatchMapping("/{id}")
    public ResponseEntity<CreateOrUpdateAd> updateAds(@PathVariable int id){
        return ResponseEntity.ok().build();

    }
    @Operation(
            tags = "Объявления",
            summary = "Получение объявлений авторизованного пользователя",
            responses = {
                    @ApiResponse(responseCode = "200",description = "OK",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Ads.class ))),
                    @ApiResponse(responseCode = "401",description = "Unauthorized",
                            content = {@Content()})
            }
    )
    @GetMapping(value = "/me")
    public ResponseEntity<?>getAdsMe(){
        return ResponseEntity.ok().build();

    }
    @Operation(
            tags = "Объявления",
            summary = "Обновление картинки объявления",
            responses = {
                    @ApiResponse(responseCode = "200",description = "OK",
                    content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE)),
                    @ApiResponse(responseCode = "403",description = "Forbidden",
                            content = {@Content()}),
                    @ApiResponse(responseCode = "401",description = " Unauthorized",
                            content = {@Content()}),
                    @ApiResponse(responseCode = "404",description = " Not found",
                            content = {@Content()})
            }
    )
    @PatchMapping(value = "{id}/image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateImage(@PathVariable int id, @RequestPart MultipartFile image){
        return ResponseEntity.status(HttpStatus.OK).build();
    }






}

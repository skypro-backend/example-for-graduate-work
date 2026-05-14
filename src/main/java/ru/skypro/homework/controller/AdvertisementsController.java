package ru.skypro.homework.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.AdDto;
import ru.skypro.homework.dto.ads.AdsDto;
import ru.skypro.homework.dto.ads.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ads.ExtendedAdDto;

@RestController
@Tag(name = "Объявления")
@RequestMapping("/ads")
public class AdvertisementsController {

    @GetMapping
    @Operation(summary = "Получение всех объявлений")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = AdsDto.class)))
    })
    public ResponseEntity<AdsDto> getAllAds() {
        return ResponseEntity.ok(new AdsDto());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Добавление объявления")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(schema = @Schema(implementation = AdDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<AdDto> addAd(
            @RequestPart("properties") CreateOrUpdateAdDto properties,
            @RequestPart("image") MultipartFile image) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new AdDto());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об объявлении")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = ExtendedAdDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<ExtendedAdDto> getAds(@PathVariable int id) {
        return ResponseEntity.ok(new ExtendedAdDto());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<Void> removeAd(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновление информации об объявлении")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = AdDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<AdDto> updateAds(@PathVariable int id,
                                           @RequestBody CreateOrUpdateAdDto dto) {
        return ResponseEntity.ok(new AdDto());
    }

    @GetMapping("/me")
    @Operation(summary = "Получение объявлений авторизованного пользователя")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = AdsDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<AdsDto> getAdsMe() {
        return ResponseEntity.ok(new AdsDto());
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление картинки объявления")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = byte[].class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<byte[]> updateImage(@PathVariable int id,
                                              @RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok(new byte[0]);
    }
}
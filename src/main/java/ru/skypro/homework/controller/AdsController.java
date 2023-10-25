package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import lombok.*;
import lombok.experimental.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import ru.skypro.homework.dto.adsDTO.*;


import javax.xml.crypto.OctetStreamData;
/**
 * Контроллер для работы c объявлениями
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("/ads")
public class AdsController {
    /**
     * Метод получения всех объявлений
     */
    @Operation(summary = "Получение всех объявлений")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping
    public ResponseEntity<AdsDTO> getAds(){
        return ResponseEntity.ok(new AdsDTO());
    }
    /**
     * Метод добавления всех объявлений
     */
    @Operation(summary = "Добавление объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "401", description = "Unauthorized")}
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDTO> addAd(@RequestParam("properties") AdDTO ad, @RequestPart MultipartFile image) {
        return ResponseEntity.ok(ad);
    }
    /**
     * Метод получения информации о объявлении
     */
    @Operation(summary = "Получение информации об объявлении")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FullAdDTO> getInfoByAd(@PathVariable int id){
        return ResponseEntity.ok(new FullAdDTO());
    }
    /**
     * Метод удаления объявления
     */
    @Operation(summary = "Удаление объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        return ResponseEntity.ok().build();
    }
    /**
     * Метод обновления информации об объявлении
     */
    @Operation(summary = "Обновление информации об объявлении")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found"),
    })
    @PatchMapping("/{id}")
    public ResponseEntity<AdDTO> updateInfoByAd(@PathVariable int id, @RequestBody CreateAdsDTO ad){
        return ResponseEntity.ok(new AdDTO());
    }
    /**
     * Метод получения объявлений авторизованного пользователя
     */
    @Operation(summary = "Получение объявлений авторизованного пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/me")
    public ResponseEntity<AdsDTO> getAdsByAuthUser(){
        return ResponseEntity.ok(new AdsDTO());
    }
    /**
     * Метод обновления картинки объявления
     */
    @Operation(summary = "Обновление картинки объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found"),
    })
    @PatchMapping(value = "/{id}/image" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<OctetStreamData> updateImage(@PathVariable int id, @RequestPart MultipartFile image) {
        return ResponseEntity.ok().build();
    }
}
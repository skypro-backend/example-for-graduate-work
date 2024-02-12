package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;

import java.util.List;

@RequestMapping("/ads")
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Объявления")
@RestController
public class AdsController {
    @Operation(
            tags = "Объявления",
            summary = "Получение всех объявлений",
            responses = {
                    @ApiResponse(responseCode = "200",description = "OK",
                    content = @Content(schema = @Schema()))


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
                    content = @Content(schema = @Schema())),
                    @ApiResponse(responseCode = "401",description = "Unauthorized",
                    content = {@Content()})
            }
    )

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addAd(@RequestBody MultipartFile image, Ads ads){
        return ResponseEntity.ok().build();
    }
    @Operation(
            tags = "Объявления",
            summary = "Получение информации об объявлении",
            responses = {
                    @ApiResponse(responseCode = "200",description = "OK",
                            content = @Content(schema = @Schema())),
                    @ApiResponse(responseCode = "401",description = "Unauthorized",
                            content = {@Content()}),
                    @ApiResponse(responseCode = "404",description = " Not found")

            }
    )

    @GetMapping("/{id}")
    public ResponseEntity<?> getAds(Ads ads){
        return ResponseEntity.ok().build();
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
            responses = {
                    @ApiResponse(responseCode = "200",description = "OK")
            }
    )
    @PatchMapping("/{id}")
    public ResponseEntity<Ads> updateAds(@RequestBody Ads ads){
        return ResponseEntity.ok(ads);

    }
    @GetMapping(value = "/me")
    public ResponseEntity<?>getAdsMe(@RequestBody Ads ads){
        return ResponseEntity.ok().build();

    }
    @PatchMapping(value = "{id}/image")
    public ResponseEntity<?> updateImage(@RequestBody MultipartFile image,
                                         Ads ads){
        return ResponseEntity.ok().build();
    }






}

package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdService;

import javax.xml.crypto.OctetStreamData;

@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("/ads")
public class AdsController {
        private final AdService adService;

        @Operation(summary = "Получение всех объявлений")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "OK")
        })
        @GetMapping
        public ResponseEntity<AdsDTO> getAds(){
            return ResponseEntity.ok().body(adService.getAllAds());
        }

        @Operation(summary = "Добавление объявления")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Created",
                        content = @Content(
                                mediaType = MediaType.APPLICATION_JSON_VALUE)),
                @ApiResponse(responseCode = "401", description = "Unauthorized")}
        )
        @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<AdDTO> addAd(@RequestPart("properties") CreateOrUpdateAdDTO ad, @RequestParam("image") MultipartFile image) {
                return ResponseEntity.ok(adService.addAd(ad,image));
        }

        @Operation(summary = "Получение информации об объявлении")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "OK"),
                @ApiResponse(responseCode = "401", description = "Unauthorized"),
                @ApiResponse(responseCode = "404", description = "Not found")
        })
        @GetMapping("/{id}")
        public ResponseEntity<ExtendedAdDTO> getInfoByAd(@PathVariable int id){
                return ResponseEntity.ok(adService.getInfoByAd(id));
        }

        @Operation(summary = "Удаление объявления")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "204", description = "No Content"),
                @ApiResponse(responseCode = "401", description = "Unauthorized"),
                @ApiResponse(responseCode = "403", description = "Forbidden"),
                @ApiResponse(responseCode = "404", description = "Not found")
        })
        @DeleteMapping("/{id}")
        public ResponseEntity deleteUser(@PathVariable int id) {
                adService.deleteUser(id);
                return ResponseEntity.ok().build();
        }

        @Operation(summary = "Обновление информации об объявлении")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "OK",content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE)),
                @ApiResponse(responseCode = "401", description = "Unauthorized"),
                @ApiResponse(responseCode = "403", description = "Forbidden"),
                @ApiResponse(responseCode = "404", description = "Not found"),
        })
        @PatchMapping("/{id}")
        public ResponseEntity<AdDTO> updateInfoByAd(@PathVariable int id, @RequestBody CreateOrUpdateAdDTO ad){
                return ResponseEntity.ok(adService.updateInfoByAd(id,ad));
        }

        @Operation(summary = "Получение информации об объявлении")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE)),
                @ApiResponse(responseCode = "401", description = "Unauthorized")
        })
        @GetMapping("/me")
        public ResponseEntity<AdsDTO> getAdsByAuthUser(){
                return ResponseEntity.ok(new AdsDTO());
        }

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
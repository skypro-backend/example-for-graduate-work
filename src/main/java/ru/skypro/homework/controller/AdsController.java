package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import liquibase.pro.packaged.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.projection.AdView;
import ru.skypro.homework.projection.Ads;
import ru.skypro.homework.projection.CreateOrUpdateAd;
import ru.skypro.homework.projection.ExtendedAd;
import ru.skypro.homework.service.ads.AdsService;

import javax.validation.Valid;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.Instant;
import java.util.List;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
@Tag(name = "Объявления")
public class AdsController {
    private final AdsService adsService;

    @GetMapping()
    @Operation(summary = "Получение всех объявлений")
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Ads.class))})
    public Ads getAllAds() {
        return adsService.getAllAds();
    }

    @Operation(summary = "Добавление объявления")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdView.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                    @Content(mediaType = "")})})
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDTO> createAd(@RequestPart(name = "properties") @Valid CreateOrUpdateAd properties,
                                          @RequestPart(name = "image") MultipartFile image) {
        return ResponseEntity.ok(adsService.createAd(properties, image)); //TODO
    }


    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об объявлении")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExtendedAd.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                    @Content(mediaType = "")}),
            @ApiResponse(responseCode = "404", description = "Not found", content = {
                    @Content(mediaType = "")})})
    public ResponseEntity<ExtendedAd> getAdFullInfo(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(adsService.getAdFullInfo(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found")})
    public void deleteAd(@PathVariable("id") Integer id) {
        adsService.deleteAd(id);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновление информации об объявлении")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content (mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdView.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                    @Content(mediaType = "")}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {
                    @Content(mediaType = "")}),
            @ApiResponse(responseCode = "404", description = "Not found", content = {
                    @Content(mediaType = "")})})
    public AdView updateAd(@PathVariable("id") Integer id,
                                           @RequestBody @Valid CreateOrUpdateAd properties) {
        return adsService.updateAd(id, properties);
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление картинки объявления")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content (mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE,
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                    @Content(mediaType = "")}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {
                    @Content(mediaType = "")}),
            @ApiResponse(responseCode = "404", description = "Not found", content = {
                    @Content(mediaType = "")})})
    public String updateImage(@PathVariable("id") Integer id,
                                              @RequestPart("image") MultipartFile image) {
        return adsService.updateImage(id, image);
    }

    @GetMapping("/me")
    @Operation(summary = "Получение объявлений авторизованного пользователя")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content (mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Ads.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                    @Content(mediaType = "")})})

    public Ads getAllAdsByUser() {
        return adsService.getAllAdsByUser();
    }
}

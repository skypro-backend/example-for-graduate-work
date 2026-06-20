package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

import java.util.Collections;

@RestController
@RequestMapping("/ads")
public class AdsController {


    @Operation(
            summary = "Получение всех объявлений",
            description = "Возвращает список всех объявлений пользователей"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ok",
            content = @Content
    )
    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        return ResponseEntity.ok(new Ads(0, Collections.emptyList()));

    }


    @Operation(
            summary = "Добавление объявления",
            description = "Создает объявление пользователя"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Created"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @PostMapping
    public ResponseEntity<Ad> addAd(
            @RequestPart("properties") CreateOrUpdateAd properties,
            @RequestPart("image") MultipartFile image) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new Ad());

    }


    @Operation(
            summary = "Получение информации об объявлении",
            description = "Возвращает полное описание объявления"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ok"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not found"
    )
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAds(@PathVariable Integer id) {
        return ResponseEntity.ok(new ExtendedAd());
    }


    @Operation(
            summary = "Удаление объявления",
            description = "Удаляет объявление пользователя"
    )
    @ApiResponse(
            responseCode = "204",
            description = "No Content"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "403",
            description = "Forbidden"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not found"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAd(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(
            summary = "Обновление информации об объявлении",
            description = "Обновляет информацию в объявлении пользователя"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ok"
    )
    @ApiResponse(
            responseCode = "403",
            description = "Forbidden"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not found"
    )
    @PatchMapping("/{id}")
    public ResponseEntity<Ad> updateAds(@PathVariable Integer id, @RequestBody CreateOrUpdateAd updateAd) {
        return ResponseEntity.status(HttpStatus.OK).body(new Ad());

    }


    @Operation(
            summary = "Получение объявлений авторизованного пользователя",
            description = "Возвращает все объявления авторизованного пользователя"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ok"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @GetMapping("/me")
    public ResponseEntity<Ads> getAdsMe() {
        return ResponseEntity.ok(new Ads(0, Collections.emptyList()));

    }


    @Operation(
            summary = "Обновление картинки объявления",
            description = "Обновляет картинку в объявлении"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ok"
    )
    @ApiResponse(
            responseCode = "403",
            description = "Forbidden"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not found"
    )
    @PatchMapping("/{id}/image")
    public ResponseEntity<Void> updateImage(@PathVariable Integer id, @RequestParam("image") MultipartFile image) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @Operation(
            summary = "Получение комментариев объявления",
            description = "Возвращает список всех комментариев к объявлению"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ok"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not found"
    )
    @GetMapping("/{id}/comments")
    public ResponseEntity<Comments> getComments(@PathVariable Integer id) {
        return ResponseEntity.ok(new Comments(0, Collections.emptyList()));
    }


    @Operation(
            summary = "Добавление комментария к объявлению",
            description = "Создает комментарий к объявлению"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ok"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not found"
    )
    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable Integer id, @RequestBody CreateOrUpdateComment comment) {
        return ResponseEntity.status(HttpStatus.OK).body(new Comment());
    }


    @Operation(
            summary = "Удаление комментария",
            description = "Удаляет комментарий пользователя"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ok"
    )
    @ApiResponse(
            responseCode = "403",
            description = "Forbidden"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not found"
    )
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer adId, @PathVariable Integer commentId) {
        return ResponseEntity.status(HttpStatus.OK).build();

    }


    @Operation(
            summary = "Обновление комментария",
            description = "Обновляет комментарий пользователя"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ok"
    )
    @ApiResponse(
            responseCode = "403",
            description = "Forbidden"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not found"
    )
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Integer adId, @PathVariable Integer commentId, @RequestBody CreateOrUpdateComment comment) {
        return ResponseEntity.ok(new Comment());
    }

}

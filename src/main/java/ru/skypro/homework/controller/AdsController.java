package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;


@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class AdsController {

    private final AdService adService;
    private final CommentService commentService;


    public AdsController(AdService adService, CommentService commentService) {
        this.adService = adService;
        this.commentService = commentService;
    }

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
        Ads ads = adService.getAllAds();
        return ResponseEntity.ok(ads);

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
            @RequestPart("image") MultipartFile image,
            Authentication authentication) {
        Ad createtedAd = adService.createdAd(properties, image, authentication);
        return ResponseEntity.status(HttpStatus.CREATED).body(createtedAd);

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
    public ResponseEntity<ExtendedAd> getAds(@PathVariable Long id) {
        ExtendedAd extendedAd = adService.getAdById(id);
        return ResponseEntity.ok(extendedAd);
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
    public ResponseEntity<Void> removeAd(@PathVariable Long id, Authentication authentication) {
        adService.deleteAd(id, authentication);
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
    public ResponseEntity<Ad> updateAds(@PathVariable Long id, @RequestBody CreateOrUpdateAd updateAd, Authentication authentication) {
        Ad updatedAd = adService.updateAd(id, updateAd, authentication);
        return ResponseEntity.status(HttpStatus.OK).body(updatedAd);

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
    public ResponseEntity<Ads> getAdsMe(Authentication authentication) {
        Ads adsMe = adService.getAdsMe(authentication);
        return ResponseEntity.ok(adsMe);

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
    public ResponseEntity<Resource> updateImage(@PathVariable Long id, @RequestParam("image") MultipartFile image, Authentication authentication) {
        Resource resource = adService.updateAdImageAndReturn(id, image, authentication);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
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
    public ResponseEntity<Comments> getComments(@PathVariable Long id) {
        Comments comments = commentService.getCommentsByAdId(id);
        return ResponseEntity.ok(comments);
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
    public ResponseEntity<Comment> addComment(@PathVariable Long id, @RequestBody CreateOrUpdateComment comment, Authentication authentication) {
        Comment createdComment = commentService.createComment(id, comment, authentication);
        return ResponseEntity.status(HttpStatus.OK).body(createdComment);
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
    public ResponseEntity<Void> deleteComment(@PathVariable Long adId, @PathVariable Long commentId, Authentication authentication) {
        commentService.deleteComment(commentId, authentication);
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
    public ResponseEntity<Comment> updateComment(@PathVariable Long adId,
                                                 @PathVariable Long commentId,
                                                 @RequestBody CreateOrUpdateComment comment,
                                                 Authentication authentication) {
        Comment updatedComment = commentService.updateComment(commentId, comment, authentication);
        return ResponseEntity.ok(updatedComment);
    }

}

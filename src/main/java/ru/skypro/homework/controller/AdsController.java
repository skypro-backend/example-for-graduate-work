package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
@Tag(name = "Объявления", description = "Управление объявлениями и комментариями к ним")
public class AdsController {

    private final AdService adService;
    private final CommentService commentService;

    @Operation(summary = "Получение всех объявлений")
    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        log.info("Request to get all ads");
        return ResponseEntity.ok(adService.getAllAds());
    }

    @Operation(summary = "Добавление объявления")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDto> addAd(@RequestPart("properties") CreateOrUpdateAd properties,
                                       @RequestPart("image") MultipartFile image,
                                       Authentication authentication) {
        log.info("Request to add new ad by: {}", authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(adService.addAd(properties, image, authentication.getName()));
    }

    @Operation(summary = "Получение информации об объявлении")
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAds(@PathVariable Integer id) {
        log.info("Request to get ad by id: {}", id);
        return ResponseEntity.ok(adService.getAd(id));
    }

    @Operation(summary = "Удаление объявления")
    @PreAuthorize("@adServiceImpl.isAuthorOrAdmin(#id)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAd(@PathVariable Integer id) {
        log.info("Request to delete ad by id: {}", id);
        adService.removeAd(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Обновление информации об объявлении")
    @PreAuthorize("@adServiceImpl.isAuthorOrAdmin(#id)")
    @PatchMapping("/{id}")
    public ResponseEntity<AdDto> updateAds(@PathVariable Integer id,
                                           @RequestBody CreateOrUpdateAd properties) {
        log.info("Request to update ad by id: {}", id);
        return ResponseEntity.ok(adService.updateAd(id, properties));
    }

    @Operation(summary = "Получение объявлений авторизованного пользователя")
    @GetMapping("/me")
    public ResponseEntity<Ads> getAdsMe(Authentication authentication) {
        log.info("Request to get current user ads for: {}", authentication.getName());
        return ResponseEntity.ok(adService.getAdsMe(authentication.getName()));
    }

    @Operation(summary = "Обновление картинки объявления")
    @PreAuthorize("@adServiceImpl.isAuthorOrAdmin(#id)")
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateImage(@PathVariable Integer id,
                                              @RequestParam MultipartFile image) {
        log.info("Request to update ad image by id: {}", id);
        adService.updateAdImage(id, image);
        return ResponseEntity.ok(new byte[0]);
    }

    // ================= COMMENT ENDPOINTS =================

    @Operation(summary = "Получение комментариев объявления")
    @GetMapping("/{id}/comments")
    public ResponseEntity<Comments> getComments(@PathVariable Integer id) {
        log.info("Request to get comments for ad id: {}", id);
        return ResponseEntity.ok(commentService.getComments(id));
    }

    @Operation(summary = "Добавление комментария к объявлению")
    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDto> addComment(@PathVariable Integer id,
                                                 @RequestBody CreateOrUpdateComment properties,
                                                 Authentication authentication) {
        log.info("Request to add comment to ad id: {} by user: {}", id, authentication.getName());
        return ResponseEntity.ok(commentService.addComment(id, properties, authentication.getName()));
    }

    @Operation(summary = "Удаление комментария")
    @PreAuthorize("@commentServiceImpl.isAuthorOrAdmin(#commentId)")
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer adId,
                                              @PathVariable Integer commentId) {
        log.info("Request to delete comment id: {} from ad id: {}", commentId, adId);
        commentService.deleteComment(adId, commentId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Обновление комментария")
    @PreAuthorize("@commentServiceImpl.isAuthorOrAdmin(#commentId)")
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Integer adId,
                                                    @PathVariable Integer commentId,
                                                    @RequestBody CreateOrUpdateComment properties) {
        log.info("Request to update comment id: {} from ad id: {}", commentId, adId);
        return ResponseEntity.ok(commentService.updateComment(adId, commentId, properties));
    }
}
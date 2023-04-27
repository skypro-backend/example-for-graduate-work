package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.comment.Comment;
import ru.skypro.homework.dto.comment.ResponseWrapperComment;
import ru.skypro.homework.dto.ads.Ads;
import ru.skypro.homework.dto.ads.CreateAds;
import ru.skypro.homework.dto.ads.FullAds;
import ru.skypro.homework.dto.ads.ResponseWrapperAds;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.CommentService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/ads")
public class AdsController {
    private final AdsService adsService;
    private final CommentService commentService;

    public AdsController( AdsService adsService, CommentService commentService ) {
        this.adsService = adsService;
        this.commentService = commentService;
    }

    @Operation(summary = "Получение всех объявлений", tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseWrapperAds.class))})
            })
    @GetMapping
    public ResponseEntity<?> getAllAds() {
        ResponseWrapperAds founded = adsService.getAllAds();
        return ResponseEntity.ok(founded);
    }

    @Operation(
            summary = "Добавление объявления", tags = "Объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "201", description = "Created",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Ads.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorised", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
            }
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public ResponseEntity<Ads> addAd( @RequestPart("properties") CreateAds createAds,
                                      @RequestPart("image") MultipartFile image,
                                      Authentication authentication ) {
        Ads result = adsService.addAd(createAds, image, authentication);
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Получение информации об объявлении", tags = "Объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = FullAds.class))}),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
            })
    @GetMapping("/{id}")
    public ResponseEntity<FullAds> getAds( @PathVariable Integer id ) {
        FullAds result = adsService.getFullAds(id);
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Обновление информации об объявлении", tags = "Объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Ads.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorised", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
            }
    )
    @PatchMapping("/{id}")
    public ResponseEntity<Ads> updateAds( @PathVariable Integer id,
                                          @RequestBody CreateAds createAds,
                                          Authentication authentication ) {
        Ads result = adsService.updateAds(id, createAds, authentication);
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Удаление объявления", tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content),
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorised", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
            }
    )
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteAds( @PathVariable Integer id, Authentication authentication ) {
        adsService.deleteAds(id, authentication);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Получение объявления авторизованного пользователя", tags = "Объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseWrapperAds.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorised", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
            }
    )
    @GetMapping("/me")
    public ResponseEntity<ResponseWrapperAds> getAdsMe( Authentication authentication ) {
        ResponseWrapperAds founded = adsService.getMyAds(authentication);
        return ResponseEntity.ok(founded);
    }

    @PatchMapping("/{id}/image")
    public ResponseEntity<MultipartFile> updateImage( @PathVariable Integer id,
                                                      @RequestBody MultipartFile image,
                                                      Authentication authentication ) {
        MultipartFile result = adsService.updateImage(id, image, authentication);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/avatar/{id}")
    public ResponseEntity<byte[]> getAdsAvatar( @PathVariable Integer id ) {
        byte[] result = adsService.getAdsAvatarById(id);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Получение комментариев объявления", tags = "Комментарии")
    @GetMapping("/{id}/comments")
    public ResponseEntity<ResponseWrapperComment> getComments( @PathVariable Integer id ) {
        ResponseWrapperComment result = commentService.getComments(id);
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Добавление нового комментария к объявлению", tags = "Комментарии",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Comment.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
            }
    )
    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment( @PathVariable Integer id,
                                               @RequestBody Comment comment,
                                               Authentication authentication ) {
        Comment result = commentService.addComment(id, comment, authentication);
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Обновление комментария", tags = "Комментарии",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Comment.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
            }
    )
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment( @PathVariable Integer adId,
                                                  @PathVariable Integer commentId,
                                                  @RequestBody Comment comment,
                                                  Authentication authentication ) {
        Comment result = commentService.updateComment(adId, commentId, comment, authentication);
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Удаление комментария", tags = "Комментарии",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
            }
    )
    @DeleteMapping("/{adId}/comments/{commentId}")
    @Transactional
    public ResponseEntity<?> deleteComment( @PathVariable Integer adId,
                                            @PathVariable Integer commentId,
                                            Authentication authentication ) {
        commentService.deleteComment(adId, commentId, authentication);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Получение объявления по заголовку", tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Ads.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
            }
    )
    @GetMapping("/title")
    public Ads getAdsByTitle( @RequestParam String title ) {
        return adsService.getAdsByTitle(title);
    }
}

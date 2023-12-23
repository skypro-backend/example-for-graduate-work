package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;


@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/ads")
public class AdController {

    private final AdService adService;
    private final CommentService commentService;

    public AdController(AdService adService, CommentService commentService) {
        this.adService = adService;
        this.commentService = commentService;
    }

    //Получаем все объявления
    @GetMapping
    public ResponseEntity<AdsDTO> getAllAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }

    //Добавляем объявление
    @PostMapping
    public ResponseEntity<AdDTO> addAd(@RequestPart(value = "properties", required = false) CreateOrUpdateAdDTO createOrUpdateAdDTO,
                                       @RequestPart("image") MultipartFile image) {
        return ResponseEntity.ok(adService.addAd(createOrUpdateAdDTO, image));

    }

    //Получаем информацию об объявлении
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDTO> getAdInfo(@PathVariable Integer id) {
        return ResponseEntity.ok(adService.getAdInfo(id));

    }

    //Удаление объявления
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAd(@PathVariable Integer id) {
        return ResponseEntity.ok((adService.deleteAd(id)));
    }

    //Обновление информации в объявлении
    @PatchMapping("/{adId}")
    public ResponseEntity<AdDTO> updateAd(@PathVariable Integer adId, @RequestBody CreateOrUpdateAdDTO createOrUpdateAdDTO) {
        return ResponseEntity.ok(adService.patchAd(adId, createOrUpdateAdDTO));
    }

    //Получение объявления авторизованного пользователя
    @GetMapping("/me")
    public ResponseEntity<AdsDTO> getAdsUser() {
        return ResponseEntity.ok(adService.getAllAdsByAuthor());
    }

    //Обновление картинки объявления
    @PatchMapping("/{id}/image")
    public ResponseEntity<Void> updateAdImage(@PathVariable Integer id, @RequestPart MultipartFile image) {
        return ResponseEntity.ok(adService.patchAdImage(id, image));
    }

//Комментарии

    //Получить комментарии об объявлении
    @GetMapping("/{id}/comments")
    public ResponseEntity<CommentsDTO> getComments(@PathVariable Integer id) {
        return ResponseEntity.ok(commentService.getComments(id));
    }

    //Добавление комментария к объявлению
    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDTO> addComment(@PathVariable Integer id,@RequestBody CreateOrUpdateCommentDTO createOrUpdateCommentDTO) {
        return ResponseEntity.ok(commentService.addComment(id, createOrUpdateCommentDTO));

    }

    //Удаление комментария
    @DeleteMapping("{id}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer id,@PathVariable Integer commentId) {
        return ResponseEntity.ok(commentService.deleteComment(id, commentId));

    }

    //Обновление комментария
    @PatchMapping("{id}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Integer id,@PathVariable Integer commentId,
                                                    @RequestBody CreateOrUpdateCommentDTO createOrUpdateCommentDTO) {
        return ResponseEntity.ok(commentService.patchComment(id, commentId, createOrUpdateCommentDTO));

    }



}


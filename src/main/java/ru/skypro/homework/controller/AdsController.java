package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.CommentService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {

    private final AdsService adsService;
    private final CommentService commentService;

    /**
     * Получение всех обьявлений
     */
    @GetMapping("/")
    public ResponseEntity<?> getAllAds() {
        return ResponseEntity.ok(adsService.getAllAds());
    }

    /**
     * Добавления обьявления
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdsDTO> addAd(
            @RequestPart("properties") CreateAds ads,
            @RequestPart("image") MultipartFile image) throws UserNotFoundException
    {
        AdsDTO ad = adsService.createAds(ads, image);
        return ResponseEntity.ok(ad);
    }

    /**
     * Получения комментария
     */
    @GetMapping("/{id}/comments")
    public ResponseEntity<ResponseWrapperComment> getComments(
            @PathVariable int id)
    {
        return ResponseEntity.ok(commentService.getUserComments(id));
    }

    /**
     * Добавления комментария
     */
    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDTO> addComment(
            @PathVariable int id,
            @RequestBody CommentDTO commentDTO)
    {
        return ResponseEntity.ok(commentService.addComment(id, commentDTO));
    }
    /**
     * Получения обьявления
     */
    @GetMapping("{id}")
    public ResponseEntity<FullAds> getAds(
            @PathVariable int id)
    {
        System.out.println("I am at getAds");
        return ResponseEntity.ok(adsService.getFullAdById(id));
    }

    /**
     * Удаления обьявления
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> removeAd(
            @PathVariable int id)
    {
        adsService.deleteAd(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Обновления обьявления
     */
    @PostMapping("{id}")
    public ResponseEntity<AdsDTO> updateAds(
            @PathVariable int id,
            @RequestBody CreateAds ads)
    {
        return ResponseEntity.ok(adsService.updateAd(id, ads));
    }

    /**
     * Удаление комментария
     */
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable int adId,
            @PathVariable int commentId)
    {
        commentService.deleteComment(adId, commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Обновление комментария
     */
    @PostMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(
            @PathVariable int adId,
            @PathVariable int commentId,
            @RequestBody CommentDTO commentDTO)
    {
        return ResponseEntity.ok().body(commentService.updateComment(adId, commentId, commentDTO));
    }

    /**
     * Получения обьявлений авторизованного пользователя
     */
    @GetMapping("/me")
    public ResponseEntity<?> getAdsMe()  {
        return ResponseEntity.ok(adsService.getAllUserAds());
    }




}
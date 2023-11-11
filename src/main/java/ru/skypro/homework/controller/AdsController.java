package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.FullAdds;
import ru.skypro.homework.service.AdsService;

import java.util.List;

@RestController
@CrossOrigin(value = "<http://localhost:3000>")
@RequestMapping("/ads")
public class AdsController {
    private final AdsService adsService;

    public AdsController(AdsService adsService) {
        this.adsService = adsService;
    }

    // --------------------------------------------------------------------------------------
    //Получить все объявления
    @GetMapping
    public ResponseEntity<?> getAds() {
        List<Ads> ads = adsService.getAds();
        return ResponseEntity.ok(ads);
    }
    // --------------------------------------------------------------------------------------

    // Добавить объявление
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> addAds(@RequestPart CreateAds properties, @RequestPart MultipartFile image) {
        adsService.addAds(properties, image);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    // --------------------------------------------------------------------------------------


    // Получить информацию об объявлении
    @GetMapping("/{id}")
    public ResponseEntity<?> getFullAd(@PathVariable int id) {
        FullAdds fullAdds = adsService.getFullAd(id);
        return ResponseEntity.ok(fullAdds);
    }
    // --------------------------------------------------------------------------------------

    // Удаалить информацию об объявлении
    @DeleteMapping("{id}")
    public ResponseEntity<?> removeAds(@PathVariable int id) {
        adsService.removeAds(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    // --------------------------------------------------------------------------------------

    //Обновить информацию об объявлении
    @PatchMapping(value = "/id", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateAds(@PathVariable int id, @RequestBody CreateAds createAds) {
        adsService.updateAds(id, createAds);
        return ResponseEntity.ok(null);
    }
    // --------------------------------------------------------------------------------------

    //Получить объявления авторизованного пользователя
    @GetMapping("me")
    public ResponseEntity<?> getAdsMe() {
        List<Ads> ads = adsService.getAdsMe();
        return ResponseEntity.ok(ads);
    }
    // --------------------------------------------------------------------------------------
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateAdsImage(@PathVariable int id, @RequestPart MultipartFile image){
        adsService.updateAdsImage(id, image);
        return ResponseEntity.ok(null);
    }

    // ======================================================================================
    // Получить комментарии объявления
    @GetMapping("{id}/comments")
    public ResponseEntity<?> getComments(@PathVariable int id) {
        List<Comment> comments = adsService.getComments(id);
        return ResponseEntity.ok(comments);
    }
    // --------------------------------------------------------------------------------------

    // Добавить комментарий к объявлению
    @PostMapping(value = "{id}/comments", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addComments(@PathVariable int id, @RequestBody Comment comment) {
        Comment res = adsService.addComments(id, comment);
        return ResponseEntity.ok(res);
    }
    // --------------------------------------------------------------------------------------

    // Удалить комментарий
    @DeleteMapping("{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComments(@PathVariable int adId, @PathVariable int commentId) {
        Comment comment = adsService.deleteComments(adId, commentId).orElse(null);
        return ResponseEntity.ok(comment);
    }
    // --------------------------------------------------------------------------------------
    // Обновить комментарий
    @PatchMapping("{adId}/comments/{commentId}")
    public ResponseEntity<?> updateComments(@PathVariable int adId, @PathVariable int commentId) {
        Comment comment = adsService.updateComments(adId, commentId).orElse(null);
        return ResponseEntity.ok(null);
    }
    // --------------------------------------------------------------------------------------
}
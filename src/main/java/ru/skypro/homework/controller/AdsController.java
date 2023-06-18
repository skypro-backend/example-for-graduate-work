package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("ads")
public class AdsController {

    @GetMapping
    public ResponseEntity<ResponseWrapperAds> getAllAds() {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Ads> addAd(@RequestBody CreateAds createAds, @RequestBody MultipartFile file) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}/comments")
    public ResponseEntity<ResponseWrapperComment> getComments(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable("id") Integer id,Comment comment) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<FullAds> getAds(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> removeAd(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<CreateAds> updateAds(@PathVariable("id") Integer id, @RequestBody Ads ads) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("adId") Integer adId, Integer commentId) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable("adId") Integer adId, Integer commentId, @RequestBody Comment comment) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("me")
    public ResponseEntity<ResponseWrapperAds> getAdsMe() {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{id}/image")
    public ResponseEntity<Byte[]> updateImage(@RequestBody MultipartFile file) {
        return ResponseEntity.ok().build();
    }
}

package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.*;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {
    //!Доработать метод
    @GetMapping("/ads")
    public ResponseEntity<ResponseWrapperAds> getAllAds() {
        return ResponseEntity.ok(new ResponseWrapperAds());
    }

    //!Доработать метод
    @PostMapping("/ads")
    public ResponseEntity<Void> addAd(@RequestBody CreateAds ads) {
       return ResponseEntity.ok().build();
    }
    //!Доработать метод
    @GetMapping("/{id}/comments")
    public ResponseEntity<ResponseWrapperComment> getComments(@PathVariable int id){
        return ResponseEntity.ok(new ResponseWrapperComment());
    }
    //!Доработать метод
    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable int id){
        return ResponseEntity.ok().build();
    }

    //!Доработать метод
    @GetMapping("/{id}")
    public ResponseEntity<FullAds> getAds(@PathVariable int id){
        return ResponseEntity.ok(new FullAds());
    }

    //!Доработать метод
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAd(@PathVariable int id){
        return ResponseEntity.ok().build();
    }

    //!Доработать метод
    @PostMapping("/{id}")
    public ResponseEntity<Ads> updateAds(@PathVariable int id ,@RequestBody CreateAds ads){
       return ResponseEntity.ok(new Ads());
    }
    //!Доработать метод
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable int adId , @PathVariable int commentId){
       return ResponseEntity.ok().build();
    }

    //!Доработать метод
    @PostMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable int adId , @PathVariable int commentId){
        return ResponseEntity.ok(new Comment());
    }

    //!Доработать метод
    @GetMapping("/ads/me")
    public ResponseEntity<ResponseWrapperAds> getAdsMe(){
        return ResponseEntity.ok(new ResponseWrapperAds());
    }

    //!Доработать метод .
    @PostMapping("/ads/{id}/image")
    public ResponseEntity<Void> updateImage(@PathVariable int id){
        return ResponseEntity.ok().build();
    }





}
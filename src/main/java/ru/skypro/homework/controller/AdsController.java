package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("ads")
public class AdsController {

    @GetMapping
    public ResponseEntity<?> getALLAds() {
        System.out.println("getallads");
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> addAds() {
        System.out.println("addAds");
        return ResponseEntity.ok().build();
    }

    @GetMapping("me")
    public ResponseEntity<?> getAdsMe() {
        System.out.println("getAdsMe");
        return ResponseEntity.ok().build();
    }

    @GetMapping("{ad_pk}/comment")
    public ResponseEntity<?> getAdsComments(@PathVariable  String ad_pk) {
        System.out.println("getAdsComments");
        return ResponseEntity.ok().build();
    }

    @PostMapping("{ad_pk}/comment")
    public ResponseEntity<?> addAdsComments(@PathVariable  String ad_pk) {
        System.out.println("addAdsComments");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{ad_pk}/comment/{id}")
    public ResponseEntity<?> deleteAdsComments(@PathVariable  String ad_pk, @PathVariable int id) {
        System.out.println("deleteAdsComments");
        return ResponseEntity.ok().build();
    }

    @GetMapping("{ad_pk}/comment/{id}")
    public ResponseEntity<?> getAdsComments(@PathVariable  String ad_pk, @PathVariable int id) {
        System.out.println("getAdsComments");
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{ad_pk}/comment/{id}")
    public ResponseEntity<?> updateAdsComments(@PathVariable  String ad_pk, @PathVariable int id) {
        System.out.println("updayeAdsComments");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> removeAds(@PathVariable int id) {
        System.out.println("removeAds");
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getAds(@PathVariable int id) {
        System.out.println("getAds");
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> updateAds(@PathVariable int id) {
        System.out.println("updateAds");
        return ResponseEntity.ok().build();
    }


}

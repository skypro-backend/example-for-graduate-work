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
@RequestMapping("ads")
public class AdsController {

    @GetMapping
    public ResponseEntity<ResponseWrapper<Ads>> getALLAds() {
        System.out.println("getallads");

        Ads ads = new Ads();
        ads.setAuthor(6);
        ads.setImage("image");
        ads.setPrice(5);
        ads.setPk(1);
        ads.setTitle("title");

        return ResponseEntity.ok(new ResponseWrapper(ads));
    }

    @PostMapping
    public ResponseEntity<Ads> addAds(@RequestBody CreateAds ads) {
        System.out.println("addAds");
        return ResponseEntity.ok().build();
    }

    @GetMapping("me")
    public ResponseEntity<ResponseWrapper<Ads>> getAdsMe(@RequestParam(required = false) Boolean authenticated,
                                      @RequestParam(required = false) String authority,
                                      @RequestParam(required = false) Object credentials,
                                      @RequestParam(required = false) Object details,
                                      @RequestParam(required = false) Object principal) {
        System.out.println("getAdsMe");
        return ResponseEntity.ok().build();
    }

    @GetMapping("{ad_pk}/comment")
    public ResponseEntity<ResponseWrapper<AdsComment>> getAdsComments(@PathVariable  String ad_pk) {
        System.out.println("getAdsComments");
        return ResponseEntity.ok().build();
    }

    @PostMapping("{ad_pk}/comment")
    public ResponseEntity<AdsComment> addAdsComments(@PathVariable  String ad_pk, @RequestBody AdsComment comment) {
        System.out.println("addAdsComments");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{ad_pk}/comment/{id}")
    public void deleteAdsComments(@PathVariable  String ad_pk, @PathVariable Integer id) {
        System.out.println("deleteAdsComments");
    }

    @GetMapping("{ad_pk}/comment/{id}")
    public ResponseEntity<AdsComment> getAdsComments(@PathVariable  String ad_pk, @PathVariable Integer id) {
        System.out.println("getAdsComments");
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{ad_pk}/comment/{id}")
    public ResponseEntity<AdsComment> updateAdsComments(@PathVariable  String ad_pk, @PathVariable Integer id, @RequestBody AdsComment comment) {
        System.out.println("updayeAdsComments");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public void removeAds(@PathVariable Integer id) {
        System.out.println("removeAds");
    }

    @GetMapping("{id}")
    public ResponseEntity<FullAds> getAds(@PathVariable Integer id) {
        System.out.println("getAds");
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<Ads> updateAds(@PathVariable Integer id, @RequestBody Ads ads) {
        System.out.println("updateAds");
        return ResponseEntity.ok().build();
    }


}

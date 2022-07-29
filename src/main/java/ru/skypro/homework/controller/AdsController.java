package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.*;

import java.util.Collection;

@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class AdsController {

    @GetMapping
    public ResponseEntity<ResponseWrapperAds> getAllAds() {
        return ResponseEntity.ok(new ResponseWrapperAds());
    }

    @PostMapping
    public ResponseEntity<Ads> addAds(@RequestBody CreateAds createAds) {
        return ResponseEntity.ok(new Ads());
    }

    @GetMapping("/me")
    public ResponseEntity<ResponseWrapperAds> getAdsMe(@RequestParam(value = "authenticated", required = false) Boolean authenticated,
                                                       @RequestParam(value = "authorities[0].authority", required = false) String authorities0Authority,
                                                       @RequestParam(value = "credentials", required = false) Object credentials,
                                                       @RequestParam(value = "details", required = false) Object details,
                                                       @RequestParam(value = "principal", required = false) Object principal) {
        return ResponseEntity.ok(new ResponseWrapperAds());
    }

    @GetMapping("/{ad_pk}/comment")
    public ResponseEntity<ResponseWrapperAdsComment> getAdsComments(@PathVariable("ad_pk") String adPk) {
        return ResponseEntity.ok(new ResponseWrapperAdsComment());
    }

    @PostMapping("/{ad_pk}/comment")
    public ResponseEntity<AdsComment> addAdsComment(@PathVariable("ad_pk") String adPk, @RequestBody AdsComment adsComment) {
        return ResponseEntity.ok(adsComment);
    }

    @DeleteMapping("/{ad_pk}/comment/{id}")
    public ResponseEntity<AdsComment> deleteAdsComment(@PathVariable("ad_pk") String adPk,
                                                       @PathVariable int id) {
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{ad_pk}/comment/{id}")
    public ResponseEntity<AdsComment> getAdsComment(@PathVariable("ad_pk") String adPk,
                                                    @PathVariable int id) {
        return ResponseEntity.ok(new AdsComment());
    }


    @PatchMapping("/{ad_pk}/comment/{id}")
    public ResponseEntity<AdsComment> updateAdsComment(@PathVariable("ad_pk") String adPk,
                                                       @PathVariable int id,
                                                       @RequestBody AdsComment adsComment) {
        return ResponseEntity.ok(adsComment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ads> removeAds(@PathVariable int id) {
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<FullAds> getAds(@PathVariable int id) {
        return ResponseEntity.ok(new FullAds());
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Ads> updateAds(@PathVariable int id, @RequestBody Ads ads) {
        return ResponseEntity.ok(ads);
    }
}
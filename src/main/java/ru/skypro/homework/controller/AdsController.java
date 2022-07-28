package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.AdsComment;

import java.util.Collection;

@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class AdsController {


    @GetMapping
    public ResponseEntity<Collection<Ads>> getAllAds() {
        return ResponseEntity.ok(null);
    }


    @PostMapping
    public ResponseEntity<Ads> addAds(@RequestBody Ads ads) {
        return ResponseEntity.ok(ads);
    }


    @GetMapping("/me")
    public ResponseEntity<Collection<Ads>> getAdsMe() {
        return ResponseEntity.ok(null);
    }



    @GetMapping("{pk}/comment")
    public ResponseEntity<Collection<AdsComment>> getAdsComments(@PathVariable int pk) {
        return ResponseEntity.ok(null);
    }


    @PostMapping("{pk}/comment")
    public ResponseEntity<Ads> addAdsComment(@PathVariable int pk, @RequestBody Ads ads) {
        return ResponseEntity.ok(ads);
    }


    @DeleteMapping("{pk}/comment/{id}")
    public ResponseEntity<AdsComment> deleteAdsComment(@PathVariable int pk,
                                                    @PathVariable int id) {
        return ResponseEntity.ok().build();
    }


    @GetMapping("{pk}/comment/{id}")
    public ResponseEntity<AdsComment> getAdsComment(@PathVariable int pk,
                                                 @PathVariable int id) {
        return ResponseEntity.ok(new AdsComment());
    }


    @PatchMapping("{pk}/comment/{id}")
    public ResponseEntity<AdsComment> updateAdsComment(@PathVariable int pk,
                                                    @PathVariable int id,
                                                    @RequestBody AdsComment comment) {
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Ads> removeAds(@PathVariable int id) {
        return ResponseEntity.ok().build();
    }


    @GetMapping("{id}")
    public ResponseEntity<Ads> getAds(@PathVariable int id) {
        return ResponseEntity.ok(new Ads());
    }


    @PatchMapping("{id}")
    public ResponseEntity<Ads> updateAds(@PathVariable int id, @RequestBody Ads ads) {
        return ResponseEntity.ok(ads);
    }
}
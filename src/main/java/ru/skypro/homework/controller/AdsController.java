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
import ru.skypro.homework.service.impl.AdsServiceImpl;

import java.util.List;

@RestController
@CrossOrigin(value = "<http://localhost:3000>")
@RequestMapping("/ads")
public class AdsController {
    private final AdsServiceImpl adsService;

    public AdsController(AdsServiceImpl adsService) {
        this.adsService = adsService;
    }

    @GetMapping
    public ResponseEntity<?> getAds() {
        List<Ads> ads = adsService.getAds();
        return ResponseEntity.ok(ads);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addAds(@RequestBody CreateAds properties, @RequestBody MultipartFile image) {
        adsService.addAds(properties, image);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("{adPk}/comments")
    public ResponseEntity<?> getComments(@PathVariable String adPk) {
        List<Comment> comments = adsService.getComments(adPk);
        return ResponseEntity.ok(comments);
    }

    @PostMapping(value = "{adPk}/comments", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addComments(@PathVariable String adPk, @RequestBody Comment comment) {
        Comment res = adsService.addComments(adPk, comment);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFullAd(@PathVariable int id) {
        FullAdds fullAdds = adsService.getFullAd(id);
        return ResponseEntity.ok(fullAdds);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> removeAds(@PathVariable int id) {
        adsService.removeAds(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping(value = "/id", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateAds(@PathVariable int id, @RequestBody CreateAds createAds) {
        adsService.updateAds(id, createAds);
        return ResponseEntity.ok(null);
    }

    @GetMapping("{adPk}/comments/{id}")
    public ResponseEntity<?> getComments(@PathVariable String adPk, @PathVariable int id) {
        adsService.getComments(adPk, id);
        return null;
    }

    @DeleteMapping("{adPk}/comments/{id}")
    public ResponseEntity<?> deleteComments(@PathVariable String adPk, @PathVariable int id) {
        Comment comment = adsService.deleteComments(adPk, id).orElse(null);
        return ResponseEntity.ok(comment);
    }

    @PatchMapping("{adPk}/comments/{id}")
    public ResponseEntity<?> updateComments(@PathVariable String adPk, @PathVariable int id) {
        Comment comment = adsService.updateComments(adPk, id).orElse(null);
        return ResponseEntity.ok(null);
    }

    @GetMapping("me")
    public ResponseEntity<?> getAdsMe() {
        List<Ads> ads = adsService.getAdsMe();
        return ResponseEntity.ok(ads);
    }
}
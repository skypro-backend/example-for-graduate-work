package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.ResponseWrapperComment;
import ru.skypro.homework.dto.ads.Ads;
import ru.skypro.homework.dto.ads.CreateAds;
import ru.skypro.homework.dto.ads.FullAds;
import ru.skypro.homework.dto.ads.ResponseWrapperAds;

import java.awt.*;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("ads")
public class AdsController {
    @GetMapping
    public ResponseWrapperAds getAllAds() {
        //200
        return null;
    }

    @PostMapping
    public Ads addAd( CreateAds createAds, Image image ) {
        //201, 401,402,404
        return null;
    }

    @GetMapping("/{id}")
    public FullAds getAds( @PathVariable Long id ) {
        //200, 404
        return null;
    }

    @PatchMapping("/{id}")
    public Ads updateAds( @PathVariable Long id ) {
        //200,401,403,404
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteAds( @PathVariable Long id ) {
        //204,401,403
    }

    @GetMapping("/me")
    public ResponseWrapperAds getAdsMe() {
        //200,401, 403
        return null;
    }

    @PatchMapping("/{id}/image")
    public String[] updateImage( @PathVariable Long id, @RequestBody String image ) {
        //200, 404
        return null;
    }

    @GetMapping("/{id}/comments")
    public ResponseWrapperComment getComments( @PathVariable Long id ) {
        //200,404
        return null;
    }

    @PostMapping("/{id}/comments")
    public Comment addComment( @PathVariable Long id, @RequestBody Comment comment ) {
        //200, 401,403, 404
        return null;
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    public Comment updateComment( @PathVariable Long adId, @PathVariable Long commentId, @RequestBody Comment comment ) {
        //200, 401, 403, 404
        return null;
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public void deleteComment( @PathVariable Long adId, @PathVariable Long commentId ) {
        //200,401,403,404
    }
}

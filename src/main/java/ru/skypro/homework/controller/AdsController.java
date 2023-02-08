package ru.skypro.homework.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.service.AdsService;

import java.util.Collection;

@RestController
@Slf4j
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Ads Controller")
public class AdsController {

    private AdsService adsService;


    public AdsController(AdsService adsService) {
        this.adsService = adsService;
    }

    @GetMapping("/{ad_pk}/comment")
    public ResponseEntity<Collection<Comment>> getAdsComments(@PathVariable(name = "ad_pk") @NonNull Integer pk) {
        return ResponseEntity.ok().body(adsService.getAdsComments(pk));
    }

    @PostMapping("/{ad_pk}/comment")
    public ResponseEntity<?> addAdsComments (@PathVariable(name = "ad_pk") @NonNull Integer pk) {
        adsService.addAdsComments(pk);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{ad_pk}/comment/{id}")
    public ResponseEntity<?> deleteAdsComment (@PathVariable(name = "ad_pk") @NonNull Integer pk,
                                               @PathVariable(name = "id") @NonNull Integer id) {
        adsService.deleteAdsComment(pk, id);
        return ResponseEntity.ok().build();
    }

}

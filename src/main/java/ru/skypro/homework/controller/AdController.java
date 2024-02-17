package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.entities.AdEntity;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.impl.AdServiceImpl;


import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/ads")
public class AdController {

    private final AdService adService;

    public AdController(AdServiceImpl adService) {
        this.adService = adService;
    }

    @GetMapping("{id}")
    public AdEntity getAds(@RequestParam Integer id) {
        return adService.getAdsById(id);
    }

    @GetMapping
    public AdsDto getAllAds() {
        return adService.getAllAds();
    }

    @DeleteMapping
    public void removeAds(@RequestParam int id) {
        adService.deleteAds(id);
    }

    @PostMapping
    public ResponseEntity<Collection<AdEntity>> addAds() {
        return new ResponseEntity<>(Collections.EMPTY_LIST, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<AdEntity> announcementInformation(@PathVariable("id") Integer id,
                                                            @RequestBody CreateOrUpdateAd dto) {
        return ResponseEntity.ok().build();

    }

    @GetMapping("/me")
    public ResponseEntity<AdsDto> getAdsAuthUser (Authentication authentication) {
        String userName = authentication.getName();
        return ResponseEntity.ok(adService.getAdsMe(userName));
    }

    @PatchMapping("{id}/image")
    public ResponseEntity<?> announcementImage(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().build();
    }
}
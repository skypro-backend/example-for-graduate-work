package ru.skypro.homework.controller;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.service.AdService;

import javax.validation.Valid;
import java.util.Collection;

@CrossOrigin(value = "http://localhost:3000")
@Slf4j
@RestController
@Validated
@RequestMapping("/ads")
public class AdController {

    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDTO> addAds(Authentication authentication, @RequestPart("properties") CreateOrUpdateAd createOrUpdateAd, @RequestPart("image") MultipartFile image) {
        return ResponseEntity.ok(adService.createAd(createOrUpdateAd, authentication));
    }

    @GetMapping
    public Collection<AdDTO> all() {
        return adService.getAll();
    }

    @GetMapping("{id}")
    public AdDTO findAdById(@PathVariable int id) {
        return adService.findAd(id);
    }

    @DeleteMapping("{id}")
    public void deleteAd(@PathVariable int id) {
        adService.deleteAd(id);
    }

    @PatchMapping("{id}")
    public CreateOrUpdateAd updateById(@PathVariable int id, @Valid @RequestBody CreateOrUpdateAd createOrUpdateAd) {
        return adService.updateAd(id, createOrUpdateAd);
    }

    @GetMapping("/me")
    public ResponseEntity<AdsDTO> getAdsMe(Authentication authentication) {
        return ResponseEntity.ok(adService.getAdsMe(authentication));
    }


}

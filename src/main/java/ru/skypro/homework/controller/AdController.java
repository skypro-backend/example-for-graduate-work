package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.service.AdService;

@Slf4j
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class AdController {

    private final AdService adService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity<ru.skypro.homework.dto.Ads> getAllAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ru.skypro.homework.dto.ExtendedAd> getAd(@PathVariable Integer id) {
        return adService.getAdById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Ad> createAd(
            @RequestPart("properties") String propertiesJson,
            @RequestPart(value = "image", required = false) MultipartFile image,
            Authentication authentication) throws Exception {

        log.info("=== CREATE AD REQUEST ===");
        log.info("Properties JSON: {}", propertiesJson);
        log.info("Image present: {}", image != null && !image.isEmpty());
        log.info("User: {}", authentication.getName());

        CreateOrUpdateAd createAd = objectMapper.readValue(propertiesJson, CreateOrUpdateAd.class);

        log.info("Title: {}", createAd.getTitle());
        log.info("Price: {}", createAd.getPrice());
        log.info("Description: {}", createAd.getDescription());

        Ad ad = adService.createAd(createAd, authentication.getName()).orElseThrow();

        if (image != null && !image.isEmpty()) {
            log.info("Saving image for ad: {}", ad.getPk());
            adService.updateAdImage(ad.getPk(), image);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(ad);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("isAuthenticated() and @adServiceImpl.isAdOwner(#id, authentication.name)")
    public ResponseEntity<Ad> updateAd(@PathVariable Integer id,
                                       @RequestBody CreateOrUpdateAd updateAd) {
        Ad ad = adService.updateAd(id, updateAd).orElseThrow();
        return ResponseEntity.ok(ad);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated() and @adServiceImpl.isAdOwner(#id, authentication.name)")
    public ResponseEntity<Void> deleteAd(@PathVariable Integer id) {
        if (adService.deleteAd(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ru.skypro.homework.dto.Ads> getMyAds(Authentication authentication) {
        return ResponseEntity.ok(adService.getAdsByUserEmail(authentication.getName()));
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("isAuthenticated() and @adServiceImpl.isAdOwner(#id, authentication.name)")
    public ResponseEntity<Void> updateAdImage(@PathVariable Integer id,
                                              @RequestParam("image") MultipartFile image) {
        adService.updateAdImage(id, image);
        return ResponseEntity.ok().build();
    }
}
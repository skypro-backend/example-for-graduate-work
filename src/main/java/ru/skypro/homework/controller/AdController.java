package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.service.AdService;

import java.io.IOException;
import java.util.Collection;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdController {

    private final AdService adService;

    @GetMapping
    public ResponseEntity <AdsDto> getAll() {
        return ResponseEntity.ok(adService.getAll());
    }
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<AdDto> createAd(@RequestPart(value = "properties") CreateOrUpdateAdDto properties,
                                          @RequestPart("image") MultipartFile image,
                                          Authentication authentication) throws IOException {
        adService.createAd(properties, image, authentication);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDto> getAdById(@PathVariable int id) {
        try {
            ExtendedAdDto ad = adService.getAdById(id);
            return ResponseEntity.ok(ad);
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable int id) {
        try {
            adService.deleteAd(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdDto> updateAd(@PathVariable int id, @RequestBody CreateOrUpdateAdDto adDto) {
        try {
            AdDto updatedAd = adService.updateAd(id, adDto);
            return ResponseEntity.ok(updatedAd);
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("/me")
    public AdsDto getAdsForCurrentUser(Authentication authentication) {
        return adService.getMyAds(authentication);
    }

    @PatchMapping("/{id}/image")
    public ResponseEntity<Void> updateAdImage(@PathVariable Integer id,
                                              @RequestBody MultipartFile image,
                                              Authentication authentication) throws IOException {
        try {
            adService.updateAdImage(id, image, authentication);
            return ResponseEntity.ok().build();
        } catch (HttpClientErrorException.Forbidden e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (HttpClientErrorException.Unauthorized e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
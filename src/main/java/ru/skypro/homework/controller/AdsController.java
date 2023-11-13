package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {
    private final AdService adService;
    private final ImageService imageService;

    @GetMapping
    public ResponseEntity<AdsDto> getAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDto> adAds(@RequestPart CreateOrUpdateAdDto properties,
                                      @RequestPart MultipartFile image, Authentication auth) throws IOException {
        return ResponseEntity.ok(adService.add(properties, image, auth.getName()));
    }

    @GetMapping("{id}")
    public ResponseEntity<ExtendedAdDto> getById(@PathVariable int id) {
        return ResponseEntity.ok(adService.getFullAdsById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@adServiceImpl.getEntity(#id).author.username.equals(#auth.name) or hasAuthority('DELETE_ANY_AD')")
    public ResponseEntity<?> removeAd(@PathVariable int id, Authentication auth) throws IOException {
        adService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PatchMapping("/{id}")
    @PreAuthorize("@adServiceImpl.getEntity(#id).author.username.equals(#auth.name) or hasAuthority('UPDATE_ANY_AD')")
    public ResponseEntity<AdDto> updateAds(@PathVariable int id, @RequestBody CreateOrUpdateAdDto ads, Authentication auth) {
        return ResponseEntity.ok(adService.update(id, ads));
    }

    @GetMapping("/me")
    public ResponseEntity<AdsDto> getAdsMe(Authentication auth) {
        return ResponseEntity.ok(adService.getAllMyAds(auth.getName()));
    }

    @PatchMapping(value = "{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateImage(@PathVariable int id,
                                         @RequestParam MultipartFile image) throws IOException {
        adService.uploadImage(id, image);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable int id) throws IOException {
        long imageId = adService.getEntity(id).getImage().getId();
        return ResponseEntity.ok(imageService.getImage(imageId));
    }
}

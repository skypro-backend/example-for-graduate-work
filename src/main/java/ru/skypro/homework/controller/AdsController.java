package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.service.AdService;

@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@Slf4j
public class AdsController {

    private final AdService adService;

    @GetMapping
    public ResponseEntity<AdsDto> getAllAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDto> addAdd(@RequestPart("properties") CreateOrUpdateAdDto dto,
                                        @RequestPart("image") MultipartFile multipartFile) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adService.addAd(multipartFile, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDto> getAd(@PathVariable Integer id) {
        return ResponseEntity.ok(adService.getAd(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or @adServiceImpl.existByAdIdAndUsername(#id, authentication.name)")
    public ResponseEntity<Void> removeAd(@PathVariable Integer id) {
        adService.removeAd(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or @adServiceImpl.existByAdIdAndUsername(#id, authentication.name)")
    public ResponseEntity<AdDto> updateAd(@PathVariable Integer id, @RequestBody CreateOrUpdateAdDto dto) {
        return ResponseEntity.ok(adService.updateAd(id, dto));
    }

    @GetMapping("/me")
    public ResponseEntity<AdsDto> getAdsMe() {
        return ResponseEntity.ok(adService.getAdsMe());
    }

    @PatchMapping(
            path = "/{id}/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    @PreAuthorize("hasAuthority('ADMIN') or @adServiceImpl.existByAdIdAndUsername(#id, authentication.name)")
    public ResponseEntity<byte[]> updateImage(@PathVariable Integer id,
                                              @RequestPart("image") MultipartFile multipartFile) {
        return ResponseEntity.ok(adService.updateAdImage(id, multipartFile));
    }

    @GetMapping(value = "/{id}/image")
    public ResponseEntity<byte[]> getAdImage(@PathVariable Integer id) {
        return ResponseEntity.ok()
                .body(adService.getImage(id));
    }
}

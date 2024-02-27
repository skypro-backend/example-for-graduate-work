package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.service.AdService;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/api/v1/ads")
@RequiredArgsConstructor
public class AdController {
    private final AdService adService;
    private final AdMapper adMapper;

    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        return ResponseEntity.ok(adMapper.toAds(adService.getAllAds()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAdById(@PathVariable Long id) {
        return ResponseEntity.ok(adMapper.toExtendedAd(adService.getAdById(id)));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDTO> createAd(
            @RequestPart("image") MultipartFile image,
            @RequestPart("properties") CreateOrUpdateAd dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adMapper.toAdDTO(adService.createAd(dto, image)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdDTO> updateAd(
            @PathVariable Long id,
            @RequestBody CreateOrUpdateAd dto) {
        return ResponseEntity.ok(adMapper.toAdDTO(adService.updateAd(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAd(@PathVariable Long id) {
        adService.deleteAd(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/me")
    public ResponseEntity<Ads> getMeAds() {
        return ResponseEntity.ok(adMapper.toAds(adService.getMyAds()));
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDTO> updateAdImage(@PathVariable("id") long id,
                                               @RequestPart(value = "image") MultipartFile image) {
        return ResponseEntity.ok(adMapper.toAdDTO(adService.updateAdImage(id, image)));
    }
}

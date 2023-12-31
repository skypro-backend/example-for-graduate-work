package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;


@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class AdsController {

    @GetMapping(" /ads")
    public ResponseEntity<AdsDTO> getAllAds() {
        return ResponseEntity.ok(new AdsDTO());
    }

    @PostMapping(value = "/ads", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDTO> addAd(@RequestPart("properties") CreateOrUpdateAdDTO createAdDTO,
                                       @RequestPart("image") MultipartFile multipartFile)  {
        return ResponseEntity.ok(new AdDTO());
    }

    @GetMapping("/ads/{id}")
    public ResponseEntity<ExtendedAdDTO> extendedAd(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(new ExtendedAdDTO());
    }

    @DeleteMapping("/ads/{id}")
    public ResponseEntity<Void> removeAd(@PathVariable(name = "id") int id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/ads/{id}")
    public ResponseEntity<AdDTO> updateAds(@PathVariable(name = "id") int id,
                                           @RequestBody CreateOrUpdateAdDTO updateAdDTO) {
        return ResponseEntity.ok(new AdDTO());
    }

    @GetMapping("/ads/me")
    public ResponseEntity<AdsDTO> getAdsMe() {
        return ResponseEntity.ok(new AdsDTO());
    }

    @PatchMapping(value = "/ads/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateImage(@PathVariable(name = "id") int id,
                                            @RequestPart("image") MultipartFile multipartFile) {
        return ResponseEntity.ok().build();
    }
}

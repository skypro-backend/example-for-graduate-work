package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class AdsController {

    @GetMapping(" /ads")
    public ResponseEntity<AdsDTO> getAllAds() {
        return ResponseEntity.ok(new AdsDTO());
    }

    @PostMapping("/ads")
    public ResponseEntity<AdDTO> addAd(@RequestBody() CreateOrUpdateAdDTO createAdDTO,
                                       @RequestBody MultipartFile multipartFile)  {
        return ResponseEntity.ok(new AdDTO());
    }

    @GetMapping("/ads/{id}")
    public ResponseEntity<ExtendedAdDTO> ExtendedAd(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(new ExtendedAdDTO());
    }

    @DeleteMapping("/ads/{id}")
    public ResponseEntity<Void> removeAd(@PathVariable(name = "id") int id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/ads/{id}")
    public ResponseEntity<AdDTO> updateAds(@PathVariable(name = "id") int id, @RequestBody CreateOrUpdateAdDTO updateAdDTO) {
        return ResponseEntity.ok(new AdDTO());
    }

    @GetMapping("/ads/me")
    public ResponseEntity<AdsDTO> getAdsMe() {
        return ResponseEntity.ok(new AdsDTO());
    }

    @PatchMapping("/ads/{id}/image")
    public ResponseEntity<Void> updateImage(@PathVariable(name = "id") int id, @RequestBody MultipartFile multipartFile) {
        return ResponseEntity.ok().build();
    }

}

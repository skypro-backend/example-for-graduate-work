package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.Ad;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdController {

    @GetMapping
    public ResponseEntity<AdsDto> getAllAds() {
        AdsDto ads = new AdsDto();
        return ResponseEntity.ok(ads);
    }

    /*@PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<AdDTO> addAd(
            @RequestPart("properties") String propertiesJson,
            @RequestPart("image") MultipartFile image) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        CreateOrUpdateAdDTO properties = objectMapper.readValue(propertiesJson, CreateOrUpdateAdDTO.class);

        AdDTO ad = new AdDTO();
        return ResponseEntity.status(HttpStatus.CREATED).body(ad);
    }*/

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ad> addAd(@RequestPart("properties") CreateOrUpdateAdDto properties,
                                    @RequestPart("image") MultipartFile image) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new Ad());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDto> getAds(@PathVariable("id") Integer id) {
        ExtendedAdDto extendedAd = new ExtendedAdDto();
        return ResponseEntity.ok(extendedAd);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAd(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdDto> updateAds(
            @PathVariable("id") Integer id,
            @RequestBody CreateOrUpdateAdDto createOrUpdateAd) {
        AdDto ad = new AdDto();
        return ResponseEntity.ok(ad);
    }

    @GetMapping("/me")
    public ResponseEntity<AdsDto> getAdsMe() {
        AdsDto ads = new AdsDto();
        return ResponseEntity.ok(ads);
    }

    @PatchMapping(value = "/{id}/image", consumes = "multipart/form-data")
    public ResponseEntity<byte[]> updateImage(
            @PathVariable("id") Integer id,
            @RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok(new byte[0]);
    }
}

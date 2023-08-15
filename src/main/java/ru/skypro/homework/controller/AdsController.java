package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.service.AdsService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/ads")
public class AdsController {

    private static final Logger logger = LoggerFactory.getLogger(AdsController.class);

    private final AdsService adsService;

    public AdsController(AdsService adsService) {
        this.adsService = adsService;
    }

    @GetMapping
    public ResponseEntity<List<AdDto>> getAds() {
        logger.info("Getting all ads");
        return ResponseEntity.ok().body(adsService.getAds());
    }

//    @PostMapping
//    public ResponseEntity<AdDto> addAd(@RequestBody AdDto adDto) {
//        logger.info("Adding new ad {}", adDto);
//        AdDto addedAd = adsService.addAd(adDto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(addedAd);
//    }

    @PostMapping
    public ResponseEntity<AdDto> addAd(@RequestBody AdDto adDto,
                                       @RequestBody MultipartFile multipartFile) {
        logger.info("Adding new ad with body {} and photo {}", adDto, multipartFile);
        AdDto addedAd = adsService.addAd(adDto, multipartFile);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedAd);
    }

    @GetMapping("/{adId}")
    public ResponseEntity<AdDto> getAd(@PathVariable int adId) {
        logger.info("Get ad with id: {} ", adId);
        return ResponseEntity.ok(adsService.getAd(adId));
    }

    @DeleteMapping("/{adId}")
    public ResponseEntity<Void> deleteAd(@PathVariable int adId) {
        logger.info("Delete ad with adId: {} ", adId);
        adsService.deleteAd(adId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{adId}")
    public ResponseEntity<AdDto> updateAd(@PathVariable int adId,
                                          @RequestBody AdDto adDto) {
        logger.info("Update ad with adId: {} ", adId);
        AdDto updatedAd = adsService.updateAd(adId, adDto);
        return ResponseEntity.ok(updatedAd);
    }

    @GetMapping("/me")
    public ResponseEntity<List<AdDto>> getMyAds() {
        logger.info("Get all user ads");
        return ResponseEntity.ok(adsService.getMyAds());
    }

    @PatchMapping("/{adId}/image")
    public ResponseEntity<HttpStatus> updateImage(@PathVariable int adId,
                                                  @RequestBody MultipartFile multipartFile) {
        adsService.updateImage(adId, multipartFile);
        return ResponseEntity.ok().build();
    }

}

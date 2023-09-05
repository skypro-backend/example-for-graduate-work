package ru.skypro.homework.controller.ads;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.AdDto;
import ru.skypro.homework.dto.ads.AdsDto;
import ru.skypro.homework.dto.ads.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ads.ExtendedAdDto;
import ru.skypro.homework.exceptions.NotFoundException;
import ru.skypro.homework.service.ads.AdsService;

import javax.validation.Valid;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@Slf4j
@Validated
@RequestMapping("/ads")
public class AdsController {

    private static final Logger logger = LoggerFactory.getLogger(AdsController.class);

    private final AdsService adsService;

    public AdsController(AdsService adsService) {
        this.adsService = adsService;
    }

    @GetMapping
    public ResponseEntity<AdsDto> getAllAds() {
        logger.info("Getting all ads");
        AdsDto adsDto = adsService.getAllAds();
        return ResponseEntity.ok(adsDto);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDto> addAd(@RequestPart("properties") @Valid CreateOrUpdateAdDto createOrUpdateAdDto,
                                       @RequestPart("image") MultipartFile image) {
        logger.info("Adding new ad with body {} and photo {}", createOrUpdateAdDto, image);
        AdDto addedAd = adsService.addAd(createOrUpdateAdDto, image);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDto> getAds(@PathVariable Integer id) {
        logger.info("Get ad with id: {} ", id);
        ExtendedAdDto extendedAdDto = adsService.getAds(id);

        if (extendedAdDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(extendedAdDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAd(@PathVariable Integer id) {
        logger.info("Delete ad with adId: {} ", id);

//        if (!adsService.doesAdExist(id)) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        if (!adsService.isAuthorizedToDelete(id)) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//
//        if (!adsService.isAllowedToDelete(id)) {
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }

//        adsService.removeAd(id);
//        return ResponseEntity.noContent().build();

        adsService.removeAd(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdDto> updateAds(@PathVariable Integer id,
                                           @RequestBody @Valid CreateOrUpdateAdDto createOrUpdateAdDto) {
        logger.info("Update ad with adId: {} ", id);
        AdDto updatedAd = adsService.updateAds(id, createOrUpdateAdDto);
        return ResponseEntity.ok(updatedAd);
    }

    @GetMapping("/me")
    public ResponseEntity<AdsDto> getAdsMe() {
        logger.info("Get all user ads");
        AdsDto adsMe = adsService.getAdsMe();
        return ResponseEntity.ok(adsMe);
    }

    @PatchMapping("/{id}/image")
    public ResponseEntity<byte[]> updateImage(@PathVariable("id") Integer id,
                                                  @RequestPart("image") MultipartFile image) {
        byte[] updatedImage = adsService.updateImage(id, image);
        if (updatedImage == null) {
            throw new NotFoundException("Объявление с таким id " + id + "не найдено");
        }
        return new ResponseEntity<>(updatedImage, HttpStatus.OK);
    }

}

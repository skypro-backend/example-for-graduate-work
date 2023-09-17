package ru.skypro.homework.controller.ads;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.out.AdDto;
import ru.skypro.homework.dto.ads.out.AdsDto;
import ru.skypro.homework.dto.ads.in.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ads.out.ExtendedAdDto;
import ru.skypro.homework.exceptions.NotFoundException;
import ru.skypro.homework.service.ads.AdsService;
import ru.skypro.homework.service.image.ImageService;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@Slf4j
@Validated
@RequestMapping("/ads")
public class AdsController {

    private final AdsService adsService;
    private final ImageService imageService;

    public AdsController(AdsService adsService, ImageService imageService) {
        this.adsService = adsService;
        this.imageService = imageService;
    }

    @GetMapping
    public ResponseEntity<AdsDto> getAllAds() {
        log.info("Getting all ads");
        AdsDto adsDto = adsService.getAllAds();
        return ResponseEntity.ok(adsDto);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDto> addAd(@RequestPart("properties") @Valid CreateOrUpdateAdDto createOrUpdateAdDto,
                                       @RequestPart("image") MultipartFile image) {
        log.info("Adding new ad with body {} and photo {}", createOrUpdateAdDto, image);
        AdDto adDto = adsService.addAd(createOrUpdateAdDto, image);
        return new ResponseEntity<>(adDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDto> getAds(@PathVariable Integer id) {
        log.info("Get ad with id: {} ", id);
        ExtendedAdDto extendedAdDto = adsService.getAds(id);

        if (extendedAdDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(extendedAdDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAd(@PathVariable Integer id) {
        log.info("Delete ad with adId: {} ", id);
        adsService.removeAd(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdDto> updateAds(@PathVariable Integer id,
                                           @RequestBody @Valid CreateOrUpdateAdDto createOrUpdateAdDto) {
        log.info("Update ad with adId: {} ", id);
        AdDto updatedAd = adsService.updateAds(id, createOrUpdateAdDto);
        return ResponseEntity.ok(updatedAd);
    }

    @GetMapping("/me")
    public ResponseEntity<AdsDto> getAdsMe() {
        log.info("Get all user ads");
        AdsDto adsMe = adsService.getAdsMe();
        return ResponseEntity.ok(adsMe);
    }

    @PatchMapping("/{id}/image")
    public ResponseEntity<byte[]> updateImage(@PathVariable("id") Integer id,
                                              @RequestPart("image") MultipartFile image) throws IOException {
        log.info("Update image {}", id);
        byte[] updatedImage = adsService.updateImage(id, image);
        if (updatedImage == null) {
            throw new NotFoundException("Объявление с таким id " + id + "не найдено");
        }
        return new ResponseEntity<>(updatedImage, HttpStatus.OK);
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Integer id) throws IOException {
        log.info("Update image {}", id);
        byte[] imageBytes = adsService.getImage(id);

        if (imageBytes == null) {
            throw new NotFoundException("У объявления с таким id " + id + "изображения не найдено");
        } else {
            ExtendedAdDto extendedAdDto = adsService.getAds(id);
            String urlToImage = extendedAdDto.getImage();
            Path fullPathToImageOfGoods = imageService.getFullPathToImageOfGoods(urlToImage);

            long fileSize = Files.size(fullPathToImageOfGoods);
            String mediaType = Files.probeContentType(fullPathToImageOfGoods);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.parseMediaType(mediaType));
            httpHeaders.setContentLength(fileSize);

            return new ResponseEntity<>(imageBytes, httpHeaders, HttpStatus.OK);
        }
    }

}


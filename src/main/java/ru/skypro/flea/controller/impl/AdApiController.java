package ru.skypro.flea.controller.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.flea.controller.AdApi;
import ru.skypro.flea.dto.AdDto;
import ru.skypro.flea.dto.AdsDto;
import ru.skypro.flea.dto.CreateOrUpdateAdDto;
import ru.skypro.flea.dto.ExtendedAdDto;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@Tag(name = "Ads")
public class AdApiController implements AdApi {

    @Override
    public ResponseEntity<AdsDto> getAllAds() {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<AdDto> addAd(MultipartFile image,
                                       CreateOrUpdateAdDto properties) {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<ExtendedAdDto> getAds(int id) {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> removeAd(int id) {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<AdDto> updateAds(int id,
                                           CreateOrUpdateAdDto properties) {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<AdsDto> getAdsMe() {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<byte[]> updateImage(int id,
                                              MultipartFile image) {
        return ResponseEntity.ok().build();
    }

}

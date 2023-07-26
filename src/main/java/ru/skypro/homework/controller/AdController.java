package ru.skypro.homework.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.dto.ResponseWrapper;
import ru.skypro.homework.service.AdsService;

import java.util.Optional;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")
@AllArgsConstructor
public class AdController {

    private final AdsService adsService;

    @GetMapping
    public ResponseWrapper<AdsDto> getAllAds() {
        return ResponseWrapper.of(adsService.getAllAds());
    }

    @PostMapping
    public ResponseEntity<AdsDto> addAd(@RequestPart("image") MultipartFile multipartFile,
                                        @RequestPart("properties") CreateOrUpdateAd createAd) {

        AdsDto adsDto = adsService.createAd(SecurityContextHolder.getContext().getAuthentication().getName(),
                multipartFile,
                createAd);

        return ResponseEntity.status(HttpStatus.CREATED).body(adsDto);

    }

    @GetMapping("{id}")
    public ResponseEntity<ExtendedAdDto> getAds(@PathVariable("id") Long id) {

        Optional<ExtendedAdDto> optionalExtendedAdDto = adsService.getExtendedAdDto(id);
        return optionalExtendedAdDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> removeAd(@PathVariable("id") Long id) {

        if (adsService.deleteByIdAd(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName(), id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @PatchMapping("{id}")
    public ResponseEntity<AdsDto> updateAds(@PathVariable("id") Long id,
                                            @RequestBody CreateOrUpdateAd updateAd) {

        Optional<AdsDto> optionalAdsDto = adsService.updateAd(id, updateAd);

        return optionalAdsDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @GetMapping("/me")
    public ResponseEntity<ResponseWrapper<AdsDto>> getAdsMe() {
        return ResponseEntity.ok(ResponseWrapper.of(adsService.getMyAds(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName())));
    }

    @PatchMapping(value = "{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateImage(@PathVariable("id") Long id, @RequestBody MultipartFile image) {

        Optional<String> responseStringOptional = adsService.changeImage(id, image);

        if (responseStringOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(responseStringOptional.get());

    }

}

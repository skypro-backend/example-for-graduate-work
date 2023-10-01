package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.model_dto.AdDto;
import ru.skypro.homework.dto.model_dto.AdsDto;
import ru.skypro.homework.dto.model_dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.model_dto.ExtendedAdDto;

import java.io.IOException;

@RestController
@RequestMapping("/ads")
public class AdsController {

    @GetMapping
    public ResponseEntity<AdsDto> getAllAds() {
        return new ResponseEntity<AdsDto>(HttpStatus.OK);
    }

    @PostMapping(value = "/multipart/form-data")
    public ResponseEntity<AdDto> addAd(@RequestBody AdDto adDTO, @RequestBody MultipartFile image) {
            return new ResponseEntity<>(adDTO, HttpStatus.CREATED);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<ExtendedAdDto> getAdInformation(@PathVariable("id") Integer id) {
            return new ResponseEntity<>(new ExtendedAdDto(), HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteAd(@PathVariable("id") Integer id) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    @PatchMapping({"/{id}"})
    public ResponseEntity<AdDto> updateAdInformation(@PathVariable("id") Integer id, @RequestBody CreateOrUpdateAdDto createOrUpdateAdDto) {
            return new ResponseEntity<>(new AdDto(), HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<AdsDto> getAuthorizedUserAds() {
        return new ResponseEntity<>(new AdsDto(), HttpStatus.OK);
    }

    @PatchMapping("/{id}/image")
    public ResponseEntity<byte[]> updateAdImage(@PathVariable("id") Integer id, @RequestPart MultipartFile image) throws IOException {
        return new ResponseEntity<>(image.getBytes(), HttpStatus.OK);
    }
}

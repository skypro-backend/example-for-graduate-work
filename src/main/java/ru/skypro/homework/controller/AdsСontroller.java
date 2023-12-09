package ru.skypro.homework.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.service.AdService;



@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsСontroller {

    private final AdService adsService;


    @GetMapping
    public ResponseEntity<AdsDto>getAllAds(){
        return ResponseEntity.ok(adsService.getAllAds());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDto> addAd(@RequestPart("properties") CreateOrUpdateAdDto dto,
                                       @RequestPart("image") MultipartFile multipartFile) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adsService.addAd(multipartFile, dto));
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ExtendedAdDto>getAds(@PathVariable Integer id) {
        return ResponseEntity.ok(adsService.getAd(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAd(@PathVariable Integer id) {
        adsService.removeAd(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<AdDto> updateAd(@PathVariable Integer id,
                                          @RequestBody CreateOrUpdateAdDto dto) {
        return ResponseEntity.ok(adsService.updateAd(id, dto));
    }

    @GetMapping(value = "/me")
    public ResponseEntity<AdsDto>getAdsMe(){
        return ResponseEntity.ok(adsService.getAdsMe());
    }

    @PatchMapping(value = "/{id}/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<byte[]> updateImage(@PathVariable Integer id,
                                              @RequestPart("image") MultipartFile multipartFile) {
        return ResponseEntity.ok(adsService.updateAdImage(id, multipartFile));
    }
}





























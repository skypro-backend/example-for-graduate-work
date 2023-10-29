package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@CrossOrigin("http://localhost:3000")
public class AdsController {

    @GetMapping
//    public AdsDto getAllAds() {
//     return new AdsDto();
//    }

    public ResponseEntity<AdsDto> getAllAds(){
        return ResponseEntity.ok(new AdsDto());
    }

//    @GetMapping
//    public ResponseEntity<AdsDto> getAllAds() {
//        var body = service.readAll();
//        return ResponseEntity.ok(body);
//    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AdDto addAd(@RequestPart(name = "properties") CreateOrUpdateAdDto dto,
                       @RequestPart(name = "image") MultipartFile image) {
       return new AdDto();
    }

//    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<AdDto> addAd(@RequestPart(name = "properties") CreateOrUpdateAdDto dto,
//                                       @RequestPart(name = "image") MultipartFile image) {
//        var body = service.create(dto, image);
//        return ResponseEntity.status(HttpStatus.CREATED).body(body);
//    }

    @GetMapping("/{id}")
    public ExtendedAdDto getAds(@PathVariable int id) {
       return new ExtendedAdDto();
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ExtendedAdDto> getAds(@PathVariable int id) {
//        var body = service.read(id);
//        return ResponseEntity.ok(body);
//    }

    @DeleteMapping("/{id}")
    public AdDto removeAd(@PathVariable int id) {
       return new AdDto();
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> removeAd(@PathVariable int id) {
//        service.delete(id);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }

    @PatchMapping("/{id}")
    public CreateOrUpdateAdDto updateAds(@PathVariable int id,
                                         @RequestBody CreateOrUpdateAdDto dto) {
      return new CreateOrUpdateAdDto();
    }

//    @PatchMapping("/{id}")
//    public ResponseEntity<AdDto> updateAds(@PathVariable int id,
//                                           @RequestBody CreateOrUpdateAdDto dto) {
//        var body = service.update(id, dto);
//        return ResponseEntity.ok(body);
//    }

    @GetMapping("/me")
    public AdsDto getAdsMe() {
        return new AdsDto();
    }

//    @GetMapping("/me")
//    public ResponseEntity<AdsDto> getAdsMe() {
//        var body = service.read();
//        return ResponseEntity.ok(body);
//    }

    @PatchMapping("{id}/image")
    public AdDto updateImage(@PathVariable int id,
                             @RequestPart MultipartFile image) {
        return new AdDto();
    }

//    @PatchMapping("{id}/image")
//    public ResponseEntity<String> updateImage(@RequestPart(name = "image") MultipartFile image) {
//        var body = service.update(id, image);
//        return ResponseEntity.ok(body);
//    }


}

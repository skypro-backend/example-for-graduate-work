package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;

import java.util.List;

@RequestMapping("/ads")
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Объявления")
@RestController
public class AdsController {
    @GetMapping
    public ResponseEntity<List<?>> getAllAds(){
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> addAds(@RequestBody Ads ads){
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAds(Ads ads){
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> removeAbs(@PathVariable int id){
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Ads> updateAds(@RequestBody Ads ads){
        return ResponseEntity.ok(ads);

    }
    @GetMapping(value = "/me")
    public ResponseEntity<?>getAdsMe(@RequestBody Ads ads){
        return ResponseEntity.ok().build();

    }
    @PatchMapping(value = "{id}/image")
    public ResponseEntity<?> updateImage(@RequestBody MultipartFile image,
                                         Ads ads){
        return ResponseEntity.ok().build();
    }






}

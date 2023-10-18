package ru.skypro.homework.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.adsDTO.AdsAllDTO;
import ru.skypro.homework.dto.adsDTO.AdsCreateDTO;

import java.util.List;

public class AdsController {

    @GetMapping("/all")
    public ResponseEntity<List<AdsAllDTO>> getAllAds() {
        return ResponseEntity.ok(AdsAllDTOService.getAllAds());
    }

    @PatchMapping (value= "/{id}/image/json"),consumes= MediaType.MULTIPART_FROM_DATA_VALUE);
    public ResponseEntity<String> updateUserImage(@PatchMapping Long id,@RequestParam MultipartFile image) {

        return ResponseEntity.ok(userService.updateUserImage(id,image);

    }

@GetMapping("/title")
    public AdsCreateDTO getAds(@PathVariable String title){
    return AdsCreateDTOService.getAds(title);
    }
    @GetMapping("/title")
    public AdsCreateDTO getAdsMe(@PathVariable String title){
        return AdsCreateDTOService.getAdsMe(title);
    }

    @DeleteMapping("/title")
    public AdsCreateDTO deleteAds(@PathVariable String title){
        return AdsCreateDTOService.deleteAds(title);
    }
}

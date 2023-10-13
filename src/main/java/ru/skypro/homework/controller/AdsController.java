package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.AdsDto;
import ru.skypro.homework.dto.ads.CreateAdsDto;
import ru.skypro.homework.dto.ads.FullAdsDto;
import ru.skypro.homework.dto.ads.ResponseWrapperAdsDto;

import java.util.ArrayList;

@RestController
@RequestMapping("/ads")
public class AdsController {


    @GetMapping()
    public AdsDto getAllAds(){
        AdsDto adsDto = new AdsDto();
        return adsDto;
    }

    @PostMapping()
    public AdsDto addAds(@RequestPart CreateAdsDto ads){
        return null;
    }

    @GetMapping("/{id}")
    public FullAdsDto getAds(@PathVariable Integer id){
        FullAdsDto fullAdsDto = new FullAdsDto();
        return fullAdsDto;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAd(@PathVariable Integer id){
        return null;
    }

    @PatchMapping("/{id}")
    public AdsDto updateAds(@PathVariable Integer id){
        return null;
    }

    @GetMapping("/me")
    public ResponseEntity<ResponseWrapperAdsDto> getAdsForMe(){
        ResponseWrapperAdsDto responseWrapperAdsDto = new ResponseWrapperAdsDto(new ArrayList<>());
        return ResponseEntity.ok(responseWrapperAdsDto);
    }

    @PatchMapping("/{id}/image")
    public byte[] updateImage(@PathVariable Integer id,
                                              @RequestPart MultipartFile image){
        return null;
    }
}


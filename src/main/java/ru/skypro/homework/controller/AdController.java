package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Объявления")
@CrossOrigin("http://localhost:3000")
@RequestMapping("/ads")
public class AdController {

    @GetMapping
    public ResponseEntity getAllAds(){
        return null;
    }
    @PostMapping
    public ResponseEntity addAd(){
        return null;
    }
    @GetMapping("/{id}")
    public ResponseEntity getAd(){
        return null;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteAd(){
        return null;
    }
    @PatchMapping("/{id}")
    public ResponseEntity updateAd(){
        return null;
    }
    @GetMapping("/me")
    public ResponseEntity getAdsMe(){
        return null;
    }
    @PatchMapping("/{id}/image")
    public ResponseEntity updateImage(@RequestParam("id") Long id){
        return null;
    }
}

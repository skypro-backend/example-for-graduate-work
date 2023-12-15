package ru.skypro.homework.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/ads")
public class AdsController {

    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        //  логика
        Ads ads = null;// получения всех объявлений
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Ad> addAd(@RequestParam("image") String image, @RequestBody CreateOrUpdateAd ad) {
        // логика
        if (image != null && ad != null) { // условие проверки наличия авторизации
            Ad newAd = null; //  добавления объявления
            return new ResponseEntity<>(newAd, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAdById(@PathVariable("id") int id) {
        ExtendedAd ad = null;// получения объявления по id
        if (ad != null) {
            return new ResponseEntity<>(ad, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ExtendedAd> removeAd(@PathVariable Long id) {
        ExtendedAd ad = null;// получения объявления по id
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        //            реализовать
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ExtendedAd> updateAd(@PathVariable Long id, @RequestBody ExtendedAd extendedAd) {
        ExtendedAd ad = null;// получения объявления по id
        if (ad != null) {
            return new ResponseEntity<>(ad, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
//            реализовать
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/me")
    public ResponseEntity<List<ExtendedAd>> getAdsMe() {
        List<ExtendedAd> emptyListOfAds = new ArrayList<>();
        if (emptyListOfAds.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(emptyListOfAds);
        }
//            реализовать
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<String>> updateImage(@PathVariable Long id, @RequestParam MultipartFile image) throws IOException {
        ExtendedAd ad = null;
        if (ad == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(image.getBytes());
            InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body((List<String>) inputStreamResource);

        }
    }


}

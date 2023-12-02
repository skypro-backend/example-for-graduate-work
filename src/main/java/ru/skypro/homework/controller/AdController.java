package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.service.AdService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;

@CrossOrigin(value = "http://localhost:3000")
@Slf4j
@RestController
@Validated
@RequestMapping("/ads")
public class AdController {

    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdDTO> addAds(@RequestPart("image") MultipartFile imageFile,
                                        Authentication authentication,
                                        @RequestPart("properties") CreateOrUpdateAd createOrUpdateAd,
                                        @RequestPart("image") MultipartFile image) throws IOException {
        return ResponseEntity.ok(adService.createAd(createOrUpdateAd, authentication, imageFile));
    }

    @GetMapping
    public ResponseEntity<AdsDTO> all() {
        return ResponseEntity.ok(adService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ExtendedAdDTO> findAdById(@PathVariable int id) {
        return ResponseEntity.ok(adService.findAd(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAd(@PathVariable int id, Authentication authentication) throws IOException {
        if (adService.deleteAd(id, authentication)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
            }

    @PatchMapping("{id}")
    public ResponseEntity<AdDTO> updateById(@PathVariable int id,
                                            @RequestBody CreateOrUpdateAd createOrUpdateAd,
                                            Authentication authentication) {
        return ResponseEntity.ok(adService.updateAd(id, createOrUpdateAd, authentication));
    }

    @GetMapping("/me")
    public ResponseEntity<AdsDTO> getAdsMe(Authentication authentication) {
        return ResponseEntity.ok(adService.getAdsMe(authentication));
    }

//    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<?> updateUserImage(@RequestParam MultipartFile image,
//                                             Authentication authentication) throws IOException {
//        userService.editUserImage(image, authentication.getName());
//        return ResponseEntity.ok().build();
//    }


}

package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.Ads;
import ru.skypro.homework.dto.ads.CreateOrUpdateAd;
import ru.skypro.homework.dto.ads.ExtendedAd;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class AdController {

    private final AdService adService;
    private final ImageService imageService;

    public AdController( AdService adService,ImageService imageService) {
        this.adService = adService;
        this.imageService = imageService;
    }

    @GetMapping
    public ResponseEntity<Ads> adInfo(Authentication authentication) {
        Ads ads = adService.allAdsPassToController();
        return ResponseEntity.ok(ads);
    }



    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addAd(@RequestPart("properties") @Valid CreateOrUpdateAd createOrUpdateAd,
                                   @RequestParam("image")@Valid MultipartFile picture, Authentication authentication) {
        ru.skypro.homework.dto.ads.Ad createdAdd = adService.newAd(createOrUpdateAd, picture, authentication.getName());

        if (createdAdd != null) {
            return ResponseEntity.ok(createdAdd);
        }
        return ResponseEntity.badRequest().build();
    }


    @PatchMapping("/{id}")
    public ResponseEntity<CreateOrUpdateAd> patchAdById (@PathVariable Integer id, @RequestBody CreateOrUpdateAd createOrUpdateAd, Authentication authentication) {
        Ad ad = adService.editPatch(createOrUpdateAd, id, authentication.getName());
        if (ad == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }



    @GetMapping("/me")
    public ResponseEntity<?> getAdsMe(Authentication authentication) {
        Ads retrievedAds = adService.authorizedUserAds(authentication.getName());
        return ResponseEntity.ok(retrievedAds);
    }



    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> patchAdPictureById (@PathVariable Integer id, @RequestParam("image") MultipartFile linkedPicture, Authentication authentication) {
        adService.patchAdPictureById(linkedPicture, id, authentication.getName());
       return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> adInfoById(@PathVariable Integer id) {
        ExtendedAd adById = adService.requestAdFromDbById(id);
        return ResponseEntity.ok(adById);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id, Authentication authentication) {
        if (adService.deleteAdById(id, authentication.getName())) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @GetMapping(value = "/{id}/avatar", produces = {MediaType.IMAGE_PNG_VALUE,MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_GIF_VALUE,"image/*"})
    public byte[] getImage(@PathVariable Integer id) throws IOException {
        Image image = imageService.callImageById(id);
        return image.getImage();
    }
}

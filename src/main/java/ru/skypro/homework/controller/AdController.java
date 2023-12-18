package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.impl.AdServiceImpl;

import java.io.IOException;
@RestController
@RequestMapping("/ads")
@CrossOrigin("http://localhost:3000/")
public class AdController {
    private final AdServiceImpl adService;
    public AdController(AdServiceImpl adService) {
        this.adService = adService;
    }

    /**
     * The method is used to get ads of all users
     * @return
     */
    @GetMapping
    public Ads getAllAds() {
        return adService.getAllAds();
    }

    /**
     * The method is used to get all ads of an authorized user
     * @param userDetails
     * @return
     */
    @GetMapping("/me")
    public ResponseEntity<Ads> getAdsByCurrentUser(@Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(adService.getAdsByCurrentUser(userDetails), HttpStatus.OK);
    }

    /**
     * The method is used to add an ad by an authorized user
     * @param image
     * @param adDetails
     * @param userDetails
     * @return
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ad> addAd(@RequestPart("image") MultipartFile image,
                    @RequestPart("properties") CreateOrUpdateAd adDetails,
                    @AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(adService.addAd(image, adDetails, userDetails),HttpStatus.CREATED);
    }



    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getFullAd(@PathVariable int id) {
        return new ResponseEntity<>(adService.getFullAd(id), HttpStatus.OK);
    }

    // Обновление информации об объявлении
    @PatchMapping("/{id}")
    public ResponseEntity<Ad> updateAd(@PathVariable int id,
                       @RequestBody CreateOrUpdateAd adDetails,
                       @AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(adService.updateAd(id, adDetails, userDetails), HttpStatus.OK);
    }


    // Удаление объявления
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAd(@PathVariable int id,
                         @AuthenticationPrincipal UserDetails userDetails) {
        adService.removeAd(id, userDetails);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Обновление картинки объявления
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateImage(@PathVariable int id,
                            @RequestParam("image") MultipartFile image,
                            @AuthenticationPrincipal UserDetails userDetails) {
        adService.updateImage(id, image, userDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}



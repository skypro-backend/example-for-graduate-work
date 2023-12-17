package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.MediaType;
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
    public Ads getAdsByCurrentUser(@Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails) {
        return adService.getAdsByCurrentUser(userDetails);
    }

    /**
     * The method is used to add an ad by an authorized user
     * @param image
     * @param adDetails
     * @param userDetails
     * @return
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Ad addAd(@RequestPart("image") MultipartFile image,
                    @RequestPart("properties") CreateOrUpdateAd adDetails,
                    @AuthenticationPrincipal UserDetails userDetails) {
        return adService.addAd(image, adDetails, userDetails);
    }



    @GetMapping("/{id}")
    public ExtendedAd getFullAd(@PathVariable int id) {
        return adService.getFullAd(id);
    }

    // Обновление информации об объявлении
    @PatchMapping("/{id}")
    public Ad updateAd(@PathVariable int id,
                       @RequestBody CreateOrUpdateAd adDetails,
                       @AuthenticationPrincipal UserDetails userDetails) {
        return adService.updateAd(id, adDetails, userDetails);
    }


    // Удаление объявления
    @DeleteMapping("/{id}")
    public void removeAd(@PathVariable int id,
                         @AuthenticationPrincipal UserDetails userDetails) {
        adService.removeAd(id, userDetails);
    }

    // Обновление картинки объявления
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateImage(@PathVariable int id,
                            @RequestParam("image") MultipartFile image,
                            @AuthenticationPrincipal UserDetails userDetails) {
        adService.updateImage(id, image, userDetails);
    }
}



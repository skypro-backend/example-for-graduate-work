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

/**
 * Controller for handling ad requests
 */
@RestController
@RequestMapping("/ads")
@CrossOrigin("http://localhost:3000/")
public class AdController {
    private final AdServiceImpl adService;
    public AdController(AdServiceImpl adService) {
        this.adService = adService;
    }

    /**
     * Gets ads of all users
     * @return Ads DTO consisting of a list of ads and the size of the list
     */
    @GetMapping
    public Ads getAllAds() {
        return adService.getAllAds();
    }

    /**
     * Gets all ads of an authorized user
     * @param userDetails contains details such as the user's username, password, authorities (roles), and additional attributes
     * @return ResponseEntity<Ads> consisting of Ads DTO(a list of ads and the size of the list) and Http status
     */
    @GetMapping("/me")
    public ResponseEntity<Ads> getAdsByCurrentUser(@Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(adService.getAdsByCurrentUser(userDetails), HttpStatus.OK);
    }

    /**
     * Adds an ad by an authorized user
     * @param image contains image URL
     * @param adDetails is CreateOrUpdateAd DTO consisting of title, price and description of an ad
     * @param userDetails contains details such as the user's username, password, authorities (roles), and additional attributes
     * @return ResponseEntity<Ad> consisting of Ad DTO(author id, image url, ad id, price, title) and Http status
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ad> addAd(@RequestPart("image") MultipartFile image,
                    @RequestPart("properties") CreateOrUpdateAd adDetails,
                    @AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(adService.addAd(image, adDetails, userDetails),HttpStatus.CREATED);
    }

    /**
     * Gets full info about ad by id
     * @param id of an ad
     * @return ResponseEntity<ExtendedAd> consisting of ExtendedAd DTO(ad id, author firstname, author lastname, description, email, image, phone, price, title) and Http status
     */
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getFullAd(@PathVariable int id) {
        return new ResponseEntity<>(adService.getFullAd(id), HttpStatus.OK);
    }

    /**
     * Updates ad info
     * @param id of an ad
     * @param adDetails is CreateOrUpdateAd DTO consisting of title, price and description of an ad
     * @param userDetails contains details such as the user's username, password, authorities (roles), and additional attributes
     * @return ResponseEntity<Ad> consisting of Ad DTO(author id, image url, ad id, price, title) and Http status
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Ad> updateAd(@PathVariable int id,
                       @RequestBody CreateOrUpdateAd adDetails,
                       @AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(adService.updateAd(id, adDetails, userDetails), HttpStatus.OK);
    }

    /**
     * Removes an ad
     * @param id of an ad
     * @param userDetails contains details such as the user's username, password, authorities (roles), and additional attributes
     * @return Http status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAd(@PathVariable int id,
                         @AuthenticationPrincipal UserDetails userDetails) {
        adService.removeAd(id, userDetails);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Updates ad image
     * @param id of an ad
     * @param image contains image URL
     * @param userDetails contains details such as the user's username, password, authorities (roles), and additional attributes
     * @return Http status
     */
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateImage(@PathVariable int id,
                            @RequestParam("image") MultipartFile image,
                            @AuthenticationPrincipal UserDetails userDetails) {
        adService.updateImage(id, image, userDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}



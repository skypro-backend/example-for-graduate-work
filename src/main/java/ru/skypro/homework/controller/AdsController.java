package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.exceptions.AdNotFoundException;
import ru.skypro.homework.service.AdService;

import javax.validation.Valid;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {

    private final AdService adService;

    @GetMapping("")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all ads",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Ads.class)) })
    })
    public ResponseEntity<Ads> getAllAds() {
        Ads ads = adService.getAllAds();
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ad created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Ad.class)) }),
            @ApiResponse(responseCode = "401", description = "Error occurred",
                    content = @Content)
    })
    public ResponseEntity<Ad> postAd(@RequestParam ("properties") CreateOrUpdateAd properties,
                                     @RequestParam("image") MultipartFile file, Authentication authentication) {
        Ad ad = adService.postAd(properties, file, authentication.getName());
        return new ResponseEntity<>(ad, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExtendedAd.class)) }),
            @ApiResponse(responseCode = "401", description = "Error occurred",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Ad not found",
                    content = @Content)
    })
    public ResponseEntity<ExtendedAd> getAdById(@PathVariable int id,
                                                Authentication authentication) throws AdNotFoundException {
        ExtendedAd extendedAd = adService.getAdById(id);
        return new ResponseEntity<>(extendedAd, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Ad not found",
                    content = @Content)
    })
    public ResponseEntity<Void> deleteAdById(@PathVariable int id, Authentication authentication) {
        adService.deleteAdById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ad patched",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Ad.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Ad not found",
                    content = @Content)
    })
    public ResponseEntity<Ad> patchAdById(@PathVariable int id,
                                         @Valid @RequestBody CreateOrUpdateAd createOrUpdateAd,
                                          Authentication authentication) throws AdNotFoundException {
        Ad ad = adService.patchAdById(id, createOrUpdateAd);
        return new ResponseEntity<>(ad, HttpStatus.OK);
    }

    @GetMapping("/me")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Ads.class)) }),
            @ApiResponse(responseCode = "401", description = "Error occurred",
                    content = @Content)
    })
    public ResponseEntity<Ads> getMyAds(Authentication authentication) {
        Ads myAds = adService.getMyAds(authentication.getName());
        return new ResponseEntity<>(myAds, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Ad not found",
                    content = @Content)
    })
    public ResponseEntity<String> patchAdsImageById(@PathVariable int id,
                                                  @RequestParam("image") MultipartFile file,
                                                  Authentication authentication) {
        adService.patchAdsImageById(id, file);
        return new ResponseEntity<>(HttpStatus.OK);
        //todo: уточнить про возвращаемый октет-стрим и переделать!!!
    }
}

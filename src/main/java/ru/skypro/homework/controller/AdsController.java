package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.service.AdService;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdsController {

    private final AdService adService;

    public AdsController(AdService adService) {
        this.adService = adService;
    }

    @Operation(summary = "Get all ads")
    @ApiResponse(responseCode = "200", description = "Ads returned")
    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }

    @Operation(summary = "Add ad")
    @ApiResponse(responseCode = "201", description = "Ad created")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @PostMapping
    public ResponseEntity<Ad> addAd(
            @RequestPart("properties") CreateOrUpdateAd properties,
            @RequestPart("image") MultipartFile image,
            Authentication authentication
    ) {
        Ad ad = adService.addAd(
                authentication.getName(),
                properties,
                image
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ad);
    }

    @Operation(summary = "Get ad")
    @ApiResponse(responseCode = "200", description = "Ad returned")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Ad not found")
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAds(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(
                adService.getAd(id)
        );
    }

    @Operation(summary = "Delete ad")
    @ApiResponse(responseCode = "204", description = "Ad deleted")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Ad not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAd(
            @PathVariable Integer id,
            Authentication authentication
    ) {
        adService.deleteAd(
                id,
                authentication.getName()
        );

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update ad")
    @ApiResponse(responseCode = "200", description = "Ad updated")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Ad not found")
    @PatchMapping("/{id}")
    public ResponseEntity<Ad> updateAds(
            @PathVariable Integer id,
            @RequestBody CreateOrUpdateAd properties,
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                adService.updateAd(
                        id,
                        properties,
                        authentication.getName()
                )
        );
    }

    @Operation(summary = "Get authorized user's ads")
    @ApiResponse(responseCode = "200", description = "Ads returned")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @GetMapping("/me")
    public ResponseEntity<Ads> getAdsMe(
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                adService.getAdsByUser(
                        authentication.getName()
                )
        );
    }

    @Operation(summary = "Update ad image")
    @ApiResponse(responseCode = "200", description = "Image updated")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Ad not found")
    @PatchMapping("/{id}/image")
    public ResponseEntity<byte[]> updateImage(
            @PathVariable Integer id,
            @RequestParam("image") MultipartFile image,
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                adService.updateImage(
                        id,
                        image,
                        authentication.getName()
                )
        );
    }

    @Operation(summary = "Get ad image")
    @ApiResponse(responseCode = "200", description = "Image returned")
    @ApiResponse(responseCode = "404", description = "Image not found")
    @GetMapping(
            value = "/{id}/image",
            produces = "application/octet-stream"
    )
    public ResponseEntity<byte[]> getImage(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(
                adService.getImage(id)
        );
    }
}
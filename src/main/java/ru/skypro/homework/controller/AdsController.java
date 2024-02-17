package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.PhotoEntity;
import ru.skypro.homework.service.AdService;

import java.io.IOException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@Tag(name = "Ads")
public class AdsController {
    private final AdService adService;
    @Operation(
            summary = "Get all ads",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "All ads received",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No ads received",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdDTO.class)
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<AdsDTO<AdDTO>> getAllAds(@RequestParam(required = false) String title) {
        AdsDTO<AdDTO> adsDTO = new AdsDTO<>(adService.getAllAds(title));
        return ResponseEntity.ok(adsDTO);

    }
    @Operation(
            summary = "Create ads",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ad successfully created",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Failed to create ad",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdDTO.class)
                            )
                    )
            }
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDTO> addAd(@RequestParam("image")@NonNull CreateOrUpdateAdDTO ad,
                                       @RequestPart MultipartFile image,
                                       @RequestParam Authentication authentication) {
        return ResponseEntity.ok(adService.createAd(ad, image, authentication));
    }

    @Operation(
            summary = "Get Ad",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ad received",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ExtendedAdDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Don't get Ad",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ExtendedAdDTO.class)
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDTO> getAdById(@PathVariable("id") int id) {
        ExtendedAdDTO extendedAdDTO = adService.getFullAd((long) id);
        return ResponseEntity.ok(extendedAdDTO);
    }

    @Operation(
            summary = "Delete Ad",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ad successfully removed",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Failed to delete ad",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdDTO.class)
                            )
                    )
            }
    )
    @PreAuthorize("@adServiceImpl.getFullAd(#id).email == authentication.name or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAd(@PathVariable int id){
        adService.deleteAd((long)id);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Update Ad",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ad successfully updated",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Failed to updated ad",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdDTO.class)
                            )
                    )
            }
    )
    @PreAuthorize("@adServiceImpl.getFullAd(#id).email == authentication.name or hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<AdDTO> updateAd(@PathVariable int id,
                                           @RequestBody CreateOrUpdateAdDTO createAdsDTO){
        return ResponseEntity.ok(adService.updateAd(createAdsDTO,(long)id));
    }
    @Operation(
            summary = "Get my ads",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "My ads have been received",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "My ads have not been received",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    )
            }
    )
    @GetMapping("/me")
    public ResponseEntity<AdsDTO<AdDTO>> getMeAd(){
        AdsDTO<AdDTO> adsDTO =
                new AdsDTO<>(adService.getUserAllAds());
        return ResponseEntity.ok(adsDTO);
    }

    @Operation(
            summary = "Update the ad image",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ad image updated successfully",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Error updating ad image",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdDTO.class)
                            )
                    )
            }
    )
    @PreAuthorize("@adServiceImpl.getFullAd(#id).email == authentication.name or hasRole('ADMIN')")
    @PatchMapping(value = "/{id}/image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateImageAd(@PathVariable int id, @RequestPart MultipartFile image) throws IOException {
        return ResponseEntity.ok(adService.updatePhoto((long) id,image));
    }
    @Operation(
            summary = "Get an image by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Image received by id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = PhotoEntity.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Image by id not received",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = PhotoEntity.class)
                            )
                    )
            }
    )
    @GetMapping(value = "/{id}/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable long id){
        return ResponseEntity.ok(adService.getAdImage(id));
    }

}
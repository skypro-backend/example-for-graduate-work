package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.AdsDto;


import java.util.List;

@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class AdsController {
    @Operation(summary = "Get the whole list of Ads", tags = "Ads")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of Ads",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )})
    })
    @GetMapping
    public ResponseEntity<List<AdsDto>> getAdsAll() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Operation(summary = "Adding an ad", tags = "Ads")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ads Adding",
                    content = {@Content(mediaType = "application/json",
                                                   schema = @Schema(implementation = AdsDto.class)
                    )})
    })
    @PostMapping
    public AdsDto addAd(@RequestBody AdsDto adsDto) {
        return new AdsDto();
    }
    @Operation(summary = "Getting information about the ad by id", tags = "Ads")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of Ads",
                    content = {@Content(mediaType = "application/json",
                                                   schema = @Schema(implementation = AdsDto.class)
                    )})
    })
    @GetMapping("/{id}")
    public ResponseEntity<List<AdsDto>> getAds(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Operation(summary = "Deleting an ad by id", tags = "Ads")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ad removed",
                    content = {@Content(mediaType = "application/json",
                                                   schema = @Schema(implementation = AdsDto.class)
                    )})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<AdsDto> deleteAds(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Operation(summary = "Updating information about the ad by id", tags = "Ads")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "the ad has been updated",
                    content = {@Content(mediaType = "application/json",
                                                   schema = @Schema(implementation = AdsDto.class)
                    )})
    })
    @PatchMapping("/{id}")
    public AdsDto updateAds(@PathVariable Long id) {
        return new AdsDto();
    }
    @Operation(summary = "We receive ads from an authorized user", tags = "Ads")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ad received",
                    content = {@Content(mediaType = "application/json",
                                                   schema = @Schema(implementation = AdsDto.class)
                    )})
    })
    @GetMapping("/me")
    public ResponseEntity<AdsDto> getAdsAuthorizedUser() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Operation(summary = "update the ad image", tags = "Ads")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "the picture has been updated",
                    content = {@Content(mediaType = "application/json",
                                                   schema = @Schema(implementation = AdsDto.class)
                    )})
    })
    @PatchMapping("/id/image")
    public AdsDto updateImage(@PathVariable Long id) {
        return new AdsDto();
    }

}

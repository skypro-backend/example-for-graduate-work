package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.AdsComment;
import ru.skypro.homework.dto.ResponseWrapperAds;
import ru.skypro.homework.dto.ResponseWrapperAdsComment;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {
    @Operation(summary = "getALLAds", tags = {"Объявления"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ResponseWrapperAds.class)))
    })
    @GetMapping
    public ResponseEntity<ResponseWrapperAds> getAllAds() {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "addAds", tags = {"Объявления"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Ads.class))),
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @PostMapping
    public ResponseEntity<Ads> addAds(@RequestBody Ads ads) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "getAdsMe", tags = {"Объявления"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ResponseWrapperAds.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
    })
    @GetMapping("/me")
    public ResponseEntity<ResponseWrapperAds> getAllMeAds() {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "getAdsComments", tags = {"Объявления"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ResponseWrapperAdsComment.class))),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @GetMapping("/{id}/comment")
    public ResponseEntity<ResponseWrapperAdsComment> getAdsComment(@PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "addAdsComments", tags = {"Объявления"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AdsComment.class))),
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @PostMapping("/{id}/comment")
    public ResponseEntity<AdsComment> addAdsComment(@PathVariable Integer id,
                                                    @RequestBody AdsComment comment) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "deleteAdsComment", tags = {"Объявления"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AdsComment.class))),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @DeleteMapping("/{id}/comment/{commentId}")
    public ResponseEntity<AdsComment> deleteAdsComment(@PathVariable Integer id,
                                                       @PathVariable Integer commentId) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "getAdsComment", tags = {"Объявления"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AdsComment.class))),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @GetMapping("/{id}/comment/{commentId}")
    public ResponseEntity<AdsComment> getAdsComment(@PathVariable Integer id,
                                                    @PathVariable Integer commentId) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "getAdsComment", tags = {"Объявления"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AdsComment.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @PatchMapping("/{id}/comment/{commentId}")
    public ResponseEntity<AdsComment> updateComments(@PathVariable Integer id,
                                                     @PathVariable Integer commentId) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "removeAds", tags = {"Объявления"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Ads.class))),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Ads> removeAds(@PathVariable Integer commentId) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "getAds", tags = {"Объявления"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Ads.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @GetMapping("/{commentId}")
    public ResponseEntity<Ads> getAds(@PathVariable Integer commentId) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "getAds", tags = {"Объявления"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Ads.class))),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
    @PatchMapping("/{commentId}")
    public ResponseEntity<Ads> updateAds(@PathVariable Integer commentId,
                                         @RequestBody Ads ads) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "updateAdsImage", tags = {"Объявления"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = MediaType.IMAGE_PNG_VALUE
            )),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
    @PatchMapping(value = "/{commentId}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateAdsImage(@PathVariable Integer commentId,
                                              @RequestPart MultipartFile image) {
        return ResponseEntity.ok().build();
    }
}

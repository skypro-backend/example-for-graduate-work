package ru.skypro.homework.controller.ads;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.out.AdDto;
import ru.skypro.homework.dto.ads.out.AdsDto;
import ru.skypro.homework.dto.ads.in.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ads.out.ExtendedAdDto;
import ru.skypro.homework.exceptions.NotFoundException;
import ru.skypro.homework.service.ads.AdsService;
import ru.skypro.homework.service.image.ImageService;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Tag(name = "Advertisements", description = "Controller to operate with ads")
@RestController
@CrossOrigin(value = "http://localhost:3000")
@Slf4j
@Validated
@RequestMapping("/ads")
public class AdsController {

    private final AdsService adsService;
    private final ImageService imageService;

    public AdsController(AdsService adsService, ImageService imageService) {
        this.adsService = adsService;
        this.imageService = imageService;
    }


    @GetMapping
    @Operation(summary = "Getting all ads of all users")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<AdsDto> getAllAds() {
        AdsDto adsDto = adsService.getAllAds();
        log.info("Got all ads");
        return ResponseEntity.ok(adsDto);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Adding new ad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AdDto.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<AdDto> addAd(
            @Parameter(description = "DTO to update Ad", required = true, schema = @Schema(implementation = CreateOrUpdateAdDto.class))
            @RequestPart("properties") @Valid CreateOrUpdateAdDto createOrUpdateAdDto,
            @Parameter(description = "Image file", required = true, content = {
                    @Content(mediaType = "image/jpg"),
                    @Content(mediaType = "image/jpeg"),
                    @Content(mediaType = "image/png")
            })
            @RequestPart("image") MultipartFile image) {
        AdDto adDto = adsService.addAd(createOrUpdateAdDto, image);
        log.info("Added new ad with body {} and photo {}", createOrUpdateAdDto, image);
        return new ResponseEntity<>(adDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get ad by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExtendedAdDto.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    public ResponseEntity<ExtendedAdDto> getAds(
            @Parameter(name = "id", description = "Id of the ad", required = true)
            @PathVariable Integer id) {
        ExtendedAdDto extendedAdDto = adsService.getAds(id);
        if (extendedAdDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("Get ad with id: {} ", id);
        return new ResponseEntity<>(extendedAdDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an ad with given Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    public ResponseEntity<Void> removeAd(
            @Parameter(name = "Id", description = "Id of the ad to delete", required = true)
            @PathVariable Integer id) {
        adsService.removeAd(id);
        log.info("Ad with id {} deleted successfully", id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update ad previously created")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AdDto.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    public ResponseEntity<AdDto> updateAds(
            @Parameter(required = true)
            @PathVariable Integer id,
            @Parameter(required = true, content = @Content(mediaType = "application/json"), schema = @Schema(implementation = CreateOrUpdateAdDto.class))
            @RequestBody @Valid CreateOrUpdateAdDto createOrUpdateAdDto) {
        AdDto updatedAd = adsService.updateAds(id, createOrUpdateAdDto);
        log.info("Ad with id: {} updated", id);
        return ResponseEntity.ok(updatedAd);
    }

    @GetMapping("/me")
    @Operation(summary = "Get all ads of the User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AdsDto.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<AdsDto> getAdsMe() {
        AdsDto adsMe = adsService.getAdsMe();
        log.info("Get all ads of the user");
        return ResponseEntity.ok(adsMe);
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @Operation(summary = "Update image of the goods")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/octet-stream")),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    public ResponseEntity<byte[]> updateImage(
            @Parameter(description = "Id of the ad", required = true)
            @PathVariable("id") Integer id,
            @Parameter(description = "Image file", required = true, content = {@Content(mediaType = "multipart/form-data", schema = @Schema(type = "string", format = "binary"))
            })
            @RequestPart("image") MultipartFile image
    ) throws IOException {
        byte[] imageBytes = adsService.updateImage(id, image);
        long fileSize = image.getSize();
        String mediaType = image.getContentType();
        if (mediaType == null) {
            mediaType = "application/octet-stream";
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(mediaType));
        httpHeaders.setContentLength(fileSize);
        log.info("Image with {} updated", id);
        return new ResponseEntity<>(imageBytes, httpHeaders, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/image", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @Operation(summary = "Get image of the good by ad id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/octet-stream")),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    public ResponseEntity<byte[]> getImage(
            @Parameter(name = "id", description = "Id of the ad", required = true)
            @PathVariable("id") Integer id) throws IOException {
        byte[] imageBytes = adsService.getImage(id);
        if (imageBytes == null) {
            throw new NotFoundException("У объявления с таким id " + id + "изображение не найдено");
        } else {
            ExtendedAdDto extendedAdDto = adsService.getAds(id);
            String urlToImage = extendedAdDto.getImage();
            Path fullPathToImageOfGoods = imageService.getFullPathToImageOfGoods(urlToImage);

            long fileSize = Files.size(fullPathToImageOfGoods);
            String mediaType = Files.probeContentType(fullPathToImageOfGoods);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.parseMediaType(mediaType));
            httpHeaders.setContentLength(fileSize);
            log.info("Update image {} ", id);
            return new ResponseEntity<>(imageBytes, httpHeaders, HttpStatus.OK);
        }
    }

}


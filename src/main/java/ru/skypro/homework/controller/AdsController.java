package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.AdDTO;
import ru.skypro.homework.dto.ads.Ads;
import ru.skypro.homework.dto.ads.CreateOrUpdateAd;
import ru.skypro.homework.dto.ads.ExtendedAd;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
//@RequiredArgsConstructor
@Tag(name = "Объявления")
public class AdsController {

    private AdService adService;

    private ImageService imageService;

    public AdsController(AdService adService, ImageService imageService) {
        this.adService = adService;
        this.imageService = imageService;
    }

    @GetMapping("/me")
    @Operation(
            summary = "Добавление объявления",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CreateOrUpdateAd.class)
                            )),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(hidden = true)))
            }
    )
    public ResponseEntity<Ads> getAdsMe() {
        return ResponseEntity.ok(new Ads());
    }

    @GetMapping
    @Operation(
            summary = "Получение всех объявлений",
            responses = {
                    @ApiResponse(responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Ads.class)
                    ))
            }
    )
    public ResponseEntity<List<AdDTO>> getAllAds() {
       return ResponseEntity.ok(adService.getAds());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получить объявление",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ExtendedAd.class)
                            )),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(hidden = true)))
            }
    )
    public ResponseEntity<Ad> getAds(@PathVariable int id) {

        AdDTO adDTO = adService.getAd(id);

        if (adDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(adDTO.toAd());
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление объявления",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(hidden = true))),

            }
    )
    public ResponseEntity removeAd(@PathVariable int id) {
        adService.deleteAd(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    @Operation(
            summary = "Добавление объявления",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CreateOrUpdateAd.class)
                            )),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(hidden = true)))
            }
    )
    public ResponseEntity<CreateOrUpdateAd> udpateAd(@PathVariable int id, @RequestBody CreateOrUpdateAd createdAd) {

        CreateOrUpdateAd createOrUpdateAd = adService.updateAd(createdAd, id);

        if (createOrUpdateAd != null) {
            return ResponseEntity.ok(adService.updateAd(createdAd, id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Создать объявление",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    schema = @Schema(implementation = CreateOrUpdateAd.class)
                            )),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(hidden = true)))
            }
    )
    public ResponseEntity<AdDTO> addAd(CreateOrUpdateAd createdAd, @RequestParam MultipartFile img) throws Exception {

        AdDTO adDTO = adService.createAd(createdAd, img);

        imageService.uploadImg(adDTO, img);

        return ResponseEntity.ok(adDTO);

    }

    @PostMapping(value = "/{id}/img", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upload(@PathVariable int id, @RequestParam MultipartFile attachment) throws Exception {

        if (attachment.getSize() >= 1024 * 1000) {
            return ResponseEntity.ok().body("File is to big");
        }

        imageService.uploadImg(adService.getAd(id), attachment);
        return ResponseEntity.ok().build();
    }


}

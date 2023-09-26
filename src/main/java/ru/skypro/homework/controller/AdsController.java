package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdsController {
    private final AdsService adsService;
    private final ImageService imageService;

    public AdsController(AdsService adsService, ImageService imageService) {
        this.adsService = adsService;
        this.imageService = imageService;
    }

    @GetMapping
    public ResponseEntity<?> ads() {
//запрос в сервис
        adsService.getAllAds();
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "AddAd",
            description = "Добавление объявления",
            tags = {"Объявления"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")})
    @PreAuthorize("isAuthenticated()")
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public AdDto createAd(@RequestPart(name = "properties") CreateOrUpdateAdDto createOrUpdateAdDto,
                          @RequestPart("image") MultipartFile image) {
        AdDto adDto = adsService.createAds(createOrUpdateAdDto, image);
        return adDto;
    }

        @Operation(
                summary = "getAd",
                description = "return info about ad",
                tags = {"Объявления"})
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200",
                        description = "OK",
                        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = AdDto.class))),
                @ApiResponse(responseCode = "401",
                        description = "Unauthorized"),
                @ApiResponse(responseCode = "404",
                        description = "Not Found")})
        @GetMapping(value = "{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
        public ResponseEntity<AdDto> getAdDto(
                @Parameter(in = ParameterIn.PATH,
                        description = "pk of ad to process")
                @PathVariable("id") Integer idAd) {
            return ResponseEntity.ok(adsService.getAdById(idAd));
        }

        @DeleteMapping("{id}")
        public ResponseEntity<?> deleteAd (@PathVariable Integer id){
//запрос в сервис
            return ResponseEntity.ok().build();
        }

        @PatchMapping("{id}")
        public ResponseEntity<?> UpdateAd (@RequestBody CreateOrUpdateAdDto createOrUpdateAd, @PathVariable Integer id){
//запрос в сервис
            return ResponseEntity.ok().build();
        }

        @GetMapping("/me")
        public ResponseEntity<?> myAds () {
//запрос в сервис
            return ResponseEntity.ok().build();
        }

        @PatchMapping("{id}/image")
        public ResponseEntity<?> UpdateImage (@RequestBody String image, @PathVariable Integer id){
//запрос в сервис
            return ResponseEntity.ok().build();
        }

        /**
         * Контроллеры для комментариев
         */
        @GetMapping("{id}/comments")
        public ResponseEntity<?> comments (@PathVariable Integer id){
//запрос в сервис
            return ResponseEntity.ok().build();
        }

        @PostMapping("{id}/comments")
        public ResponseEntity<?> createComment (@RequestBody CreateOrUpdateComment
        createOrUpdateComment, @PathVariable Integer id){
            //запрос в сервис
            return ResponseEntity.ok().build();
        }

        @DeleteMapping("{id}/comments/{commentId}")
        public ResponseEntity<?> deleteComment (@PathVariable Integer id, @PathVariable Integer commentId){
//запрос в сервис
            return ResponseEntity.ok().build();
        }

        @PatchMapping("{id}/comments/{commentId}")
        public ResponseEntity<?> UpdateComment (@PathVariable Integer id,
                @PathVariable Integer commentId,
                @RequestBody CreateOrUpdateAdDto createOrUpdateAd){
//запрос в сервис
            return ResponseEntity.ok().build();
        }
    private ResponseEntity<byte[]> read(String id) {
        return ResponseEntity.ok(imageService.getImage(id));

    }

    }

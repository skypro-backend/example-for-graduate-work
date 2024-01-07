package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.impl.ValidationServiceImpl;

import java.io.IOException;
import java.util.Collection;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdController {

    private final AdService adService;
    private final ImageService imageService;
    private final AdRepository adRepository;

    @GetMapping
    public ResponseEntity <AdsDto> getAllAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<AdDto> addAd(@RequestPart(value = "properties") CreateOrUpdateAdDto properties,
                                          @RequestPart("image") MultipartFile image,
                                          Authentication authentication) throws IOException {
        adService.addAd(properties, image, authentication);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об объявлении", description = "getAds", tags = {"Объявления"})
    public ResponseEntity<ExtendedAdDto> getAds(@PathVariable int id) {
        try {
            ExtendedAdDto ad = adService.getAds(id);
            return ResponseEntity.ok(ad);
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAd(@PathVariable Integer id,Authentication authentication) {
        if (authentication == null || authentication.getName() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String userEmail = adRepository.findAdById(id).getAuthor().getEmail();
        boolean isAdminOrOwner = ValidationServiceImpl.isAdmin(authentication, userEmail);
        ExtendedAdDto foundAd = adService.findExtendedAd(id);

        if (!isAdminOrOwner) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else if (foundAd == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        adService.removeAd(id, authentication);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdDto> updateAds(@PathVariable int id, @RequestBody CreateOrUpdateAdDto adDto) {
        try {
            AdDto updatedAd = adService.updateAds(id, adDto);
            return ResponseEntity.ok(updatedAd);
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("/me")
    @Operation(summary = "Получение объявлений авторизованного пользователя", description = "getAdsMe", tags = {"Объявления"})
    public ResponseEntity<AdsDto> getAdsMe(Authentication authentication) {
        return ResponseEntity.ok(adService.getMyAds(authentication));
    }

    @PatchMapping(value = "/{id}/image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление картинки объявления", description = "updateImage", tags = {"Объявления"})
    public ResponseEntity<?> updateImage(@Parameter(description = "ID объявления") @PathVariable("id") Integer id,
                                         @RequestPart("image") MultipartFile image, Authentication authentication){
        adService.updateImage(id,image,authentication);
        return ResponseEntity.ok().build();
    }
    @GetMapping(value = "/image/{id}", produces = {MediaType.IMAGE_PNG_VALUE,
            MediaType.IMAGE_JPEG_VALUE,
            MediaType.IMAGE_GIF_VALUE
    })
    @Operation(summary = "Получение картинки объявления", description = "getAdsImage", tags = {"Объявления"})
    public ResponseEntity<byte[]> getAdsImage(@PathVariable("id") long id) {
        byte[] image = imageService.getImage(id).getData();
        return ResponseEntity.ok(image);
    }

}
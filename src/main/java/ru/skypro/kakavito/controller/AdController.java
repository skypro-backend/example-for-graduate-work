package ru.skypro.kakavito.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.kakavito.dto.AdDTO;
import ru.skypro.kakavito.dto.AdsDTO;
import ru.skypro.kakavito.dto.CreateOrUpdateAdDTO;
import ru.skypro.kakavito.dto.ExtendedAdDTO;
import ru.skypro.kakavito.exceptions.ImageSizeExceededException;
import ru.skypro.kakavito.service.AdService;

import java.io.IOException;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")
@RequiredArgsConstructor
public class AdController {
    private final AdService adService;


    @GetMapping()
    public ResponseEntity<AdsDTO> findAll() {
        AdsDTO ads = adService.findAll();
        if (ads.getCount() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(ads);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDTO> getById(@PathVariable Long id) {
        ExtendedAdDTO ad = adService.findById(id);
        if (ad == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ad);
    }

    @GetMapping("/me")
    public ResponseEntity<AdsDTO> getAdByMe() {
        return ResponseEntity.ok(adService.getAdByAuthUser());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDTO> addAd(@RequestPart(value = "properties") CreateOrUpdateAdDTO createOrUpdateAdDTO,
                                       @RequestPart("image") MultipartFile imageFile) throws IOException, ImageSizeExceededException {
        AdDTO ad = adService.addAd(createOrUpdateAdDTO, imageFile);
        if (ad == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ad, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdDTO> updateAd(@PathVariable Long id,
                                          @RequestBody CreateOrUpdateAdDTO createOrUpdateAdDTO) {
        AdDTO updatedAd = adService.updateAd(id, createOrUpdateAdDTO);
        if (updatedAd == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(updatedAd);
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateAdImage(@PathVariable Long id, @RequestBody MultipartFile image) throws IOException {
        adService.updateImage(id, image);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReportById(@PathVariable Long id) {
        adService.deleteAd(Math.toIntExact(id));
        return ResponseEntity.ok().build();
    }

}

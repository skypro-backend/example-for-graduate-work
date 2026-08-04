package ru.skypro.homework.controller.ads;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.data.dto.ListResponseDto;
import ru.skypro.homework.data.dto.ads.AdItemDto;
import ru.skypro.homework.data.dto.ads.CreateOrUpdateAd;
import ru.skypro.homework.data.dto.ads.ExtendedAdItem;

import java.util.List;

@RestController
@RequestMapping("/ads")
public class AdsController {


    @GetMapping
    public ResponseEntity<ListResponseDto<AdItemDto>> getAdsList() {
        return ResponseEntity.ok(new ListResponseDto<>());
    }

    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<AdItemDto> saveAd(
            @ModelAttribute CreateOrUpdateAd postAdRequest
    ) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdItem> getAdDetails(@PathVariable Integer id) {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AdItemDto> deleteAd(@PathVariable Integer id) {
        return ResponseEntity.ok(null);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdItemDto> updateAd(
            CreateOrUpdateAd updateAdRequest,
            @PathVariable String id) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/me")
    public ResponseEntity<ListResponseDto<AdItemDto>> getMyAds() {
        return ResponseEntity.ok(new ListResponseDto<>());
    }

    @PatchMapping("/{id}/image")
    public ResponseEntity<List<String>> updateAdImage(
            @RequestParam("image") MultipartFile image
    ) {
        return ResponseEntity.ok(null);
    }


}

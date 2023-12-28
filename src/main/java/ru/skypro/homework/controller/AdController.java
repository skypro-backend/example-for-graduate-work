package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.mappers.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.service.AdService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class AdController {
    private final AdService adService;

    private final AdMapper adMapper;

    @GetMapping()
    public ResponseEntity<List<AdsDTO>> getAllAds() {
        List<Ad> ads = adService.findAll();
        if (ads.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return adService.findAll().stream().map(adMapper.convertToAdsDTO((ad))
                .collect(Collectors.toList()));
    }

    @GetMapping("{id}")
    public AdDTO getById(@PathVariable Long id) {
        return adMapper.convertToAdDTO(adService.findById(id));
    }

    @GetMapping("/me")
    public List<ExtendedAdDTO> getAdByMe() {
//        List<Ad> ads = adService.getAdByAuthUser();
        return adService.getAdByAuthUser().stream().map(adMapper.convertToExtendedAd())
                .collect(Collectors.toList());
    }
    @PostMapping()
    public ResponseEntity<Void> addAd(@RequestBody CreateOrUpdateAdDTO ad, @RequestBody byte[] img) {
        if (ad == null) {
            return ResponseEntity.notFound().build();
        }
//        adService.addAd(ad, img);
        return adMapper.convertCreatDTOToAd(adService.addAd(ad, img));
    }

    @PatchMapping("{id}")
    public ResponseEntity<Void> updateAd(@PathVariable Long id, @RequestBody CreateOrUpdateAdDTO ad) {
        Ad foundAd = adService.findById(id);
        if (foundAd == null) {
            return ResponseEntity.notFound().build();
        }
//        adService.updateAd(ad);
        return adMapper.convertCreatDTOToAd(adService.updateAd(ad));
    }

    @PatchMapping("{id}/image")
    public ResponseEntity<Void> updateImageAd(@PathVariable Long id, @RequestBody byte[] img) {
        Ad foundAd = adService.findById(id);
        if (foundAd == null) {
            return ResponseEntity.notFound().build();
        }
        adService.updateImage(img);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteReportById(@PathVariable Long id) {
        Ad ad = adService.findById(id);
        if (ad == null) {
            return ResponseEntity.notFound().build();
        }
        adService.deleteAd(ad);
        return ResponseEntity.ok().build();
    }

}

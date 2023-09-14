package ru.skypro.homework.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.AdDTO;
import ru.skypro.homework.dto.ad.CreateAdRequest;
import ru.skypro.homework.dto.ad.UpdateAdRequest;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.service.impl.AdService;

import java.util.List;

@RestController
@RequestMapping("/ads")
public class AdController {

    private final AdService adService;
    private final AdMapper adMapper;
    private static final Logger logger = LoggerFactory.getLogger(AdController.class);

    @Autowired
    public AdController(AdService adService, AdMapper adMapper) {
        this.adService = adService;
        this.adMapper = adMapper;
    }

    @GetMapping("/")
    public List<AdDTO> getAllAds() {
        try {
            return adService.getAllAds();
        } catch (Exception e) {
            logger.error("An error occurred while fetching all ads", e);
            throw e; // Rethrow the exception to let Spring handle it
        }
    }

    @GetMapping("/{adId}")
    public AdDTO getAdById(@PathVariable Long adId) {
        try {
            return adService.getAdById(adId);
        } catch (Exception e) {
            logger.error("An error occurred while fetching ad by ID: " + adId, e);
            throw e;
        }
    }

    @PostMapping("/")
    public AdDTO createAd(@RequestBody CreateAdRequest createAdRequest) {
        try {
            return adService.createAd(createAdRequest);
        } catch (Exception e) {
            logger.error("An error occurred while creating ad", e);
            throw e;
        }
    }

    @PutMapping("/{adId}")
    @PreAuthorize("hasRole('USER') and @adService.isAdOwner(authentication, #adId)")
    public AdDTO updateAd(@PathVariable Long adId, @RequestBody UpdateAdRequest updateAdRequest) {
        try {
            return adService.updateAd(adId, updateAdRequest);
        } catch (Exception e) {
            logger.error("An error occurred while updating ad with ID: " + adId, e);
            throw e;
        }
    }

    @DeleteMapping("/{adId}")
    @PreAuthorize("hasRole('USER') and @adService.isAdOwner(authentication, #adId)")
    public void deleteAd(@PathVariable Long adId) {
        try {
            adService.deleteAd(adId);
        } catch (Exception e) {
            logger.error("An error occurred while deleting ad with ID: " + adId, e);
            throw e;
        }
    }
    @PutMapping("/{adId}/image")
    @PreAuthorize("hasRole('USER') and @adService.isAdOwner(authentication, #adId)")
    public ResponseEntity<?> updateAdImage(
            @PathVariable Long adId,
            @RequestParam("image") MultipartFile imageFile
    ) {
        try {
            AdDTO adDTO = adService.getAdById(adId);
            byte[] imageData = imageFile.getBytes();
            Ad ad = adMapper.adDTOToAd(adDTO);
            ad.setImage(imageData);
            adService.updateAdImage(ad);

            return ResponseEntity.ok(adDTO);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update ad image.");
        }
    }
}
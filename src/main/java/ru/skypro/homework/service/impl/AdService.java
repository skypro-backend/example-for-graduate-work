package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.ad.AdDTO;
import ru.skypro.homework.dto.ad.CreateAdRequest;
import ru.skypro.homework.dto.ad.UpdateAdRequest;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdService {

    private final AdRepository adRepository;
    private final AdMapper adMapper;
    private final Logger logger = LoggerFactory.getLogger(AdService.class);

    @Autowired
    public AdService(AdRepository adRepository, AdMapper adMapper) {
        this.adRepository = adRepository;
        this.adMapper = adMapper;
    }

    public List<AdDTO> getAllAds() {
        try {
            List<Ad> ads = adRepository.findAll();
            return ads.stream().map(adMapper::adToAdDTO).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("An error occurred while getting all ads: {}", e.getMessage());
            throw new RuntimeException("Failed to retrieve ads.", e);
        }
    }

    public AdDTO getAdById(Long adId) {
        try {
            Optional<Ad> optionalAd = adRepository.findById(adId);
            Ad ad = optionalAd.orElseThrow(() -> new IllegalArgumentException("Ad not found with id: " + adId));
            return adMapper.adToAdDTO(ad);
        } catch (IllegalArgumentException e) {
            logger.error("Ad not found with id: {}", adId);
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred while getting ad by id {}: {}", adId, e.getMessage());
            throw new RuntimeException("Failed to retrieve ad.", e);
        }
    }

    public AdDTO createAd(CreateAdRequest createAdRequest) {
        try {
            Ad ad = new Ad();
            ad.setTitle(createAdRequest.getTitle());
            ad.setPrice(createAdRequest.getPrice());
            ad.setDescription(createAdRequest.getDescription());

            ad = adRepository.save(ad);

            return adMapper.adToAdDTO(ad);
        } catch (Exception e) {
            logger.error("An error occurred while creating ad: {}", e.getMessage());
            throw new RuntimeException("Failed to create ad.", e);
        }
    }

    public AdDTO updateAd(Long adId, UpdateAdRequest updateAdRequest) {
        try {
            Ad ad = adRepository.findById(adId)
                    .orElseThrow(() -> new IllegalArgumentException("Ad not found with id: " + adId));

            ad.setTitle(updateAdRequest.getTitle());
            ad.setPrice(updateAdRequest.getPrice());
            ad.setDescription(updateAdRequest.getDescription());

            ad = adRepository.save(ad);

            return adMapper.adToAdDTO(ad);
        } catch (IllegalArgumentException e) {
            logger.error("Ad not found with id: {}", adId);
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred while updating ad with id {}: {}", adId, e.getMessage());
            throw new RuntimeException("Failed to update ad.", e);
        }
    }

    public void deleteAd(Long adId) {
        try {
            adRepository.deleteById(adId);
        } catch (Exception e) {
            logger.error("An error occurred while deleting ad with id {}: {}", adId, e.getMessage());
            throw new RuntimeException("Failed to delete ad.", e);
        }
    }
    public boolean isAdOwner(Authentication authentication, Long adId) {
        Ad ad = adRepository.findById(adId).orElse(null);
        User currentUser = (User) authentication.getPrincipal();
        return ad != null && currentUser != null && ad.getUser().getId().equals(currentUser.getId());
    }

    public void updateAdImage(Ad ad) {

        adRepository.save(ad);
    }
}
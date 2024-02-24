package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;

import java.io.IOException;
import java.util.Collection;

public interface AdsService {
    Collection<Ads> getAllAds();
    Collection<AdEntity> getAdsMe(Authentication authentication);

    CreateOrUpdateAd addAds(CreateOrUpdateAd createOrUpdateAd, MultipartFile imageFiles, Authentication authentication) throws IOException;
    AdEntity removeAdsById(Long id);


    AdEntity updateAds(Long adId, CreateOrUpdateAd createOrUpdateAd, Authentication authentication);

    void updateAdsImage(long id, MultipartFile image, Authentication authentication);
     ExtendedAd getAds(Long id);
}

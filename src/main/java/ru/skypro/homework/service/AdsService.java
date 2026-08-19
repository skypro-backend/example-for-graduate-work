package ru.skypro.homework.service;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

public interface AdsService {
    Ads getAllAds();
    Ad addAd(CreateOrUpdateAd properties, MultipartFile image, Authentication authentication);
    ExtendedAd getAds(Long id);
    void removeAd(Long id, Authentication authentication);
    Ad updateAds(Long id, CreateOrUpdateAd properties, Authentication authentication);
    Ads getAdsMe(Authentication authentication);
    byte[] updateImage(Long id, MultipartFile image, Authentication authentication);
}
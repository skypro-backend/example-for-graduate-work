package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

public interface AdService {
    Ads getAllAds();
    AdDto addAd(CreateOrUpdateAd properties, MultipartFile image, String username);
    ExtendedAd getAd(Integer id);
    void removeAd(Integer id);
    AdDto updateAd(Integer id, CreateOrUpdateAd properties);
    Ads getAdsMe(String username);
    void updateAdImage(Integer id, MultipartFile image);
}
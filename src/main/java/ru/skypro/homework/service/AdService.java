package ru.skypro.homework.service;

import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import org.springframework.web.multipart.MultipartFile;

public interface AdService {
    Ads getAllAds();
    Ad addAd(CreateOrUpdateAd createOrUpdateAd, MultipartFile image, String username);
    ExtendedAd getAd(Integer id);
    void removeAd(Integer id, String username);
    Ad updateAd(Integer id, CreateOrUpdateAd createOrUpdateAd, String username);
    Ads getAdsMe(String username);
    String updateImage(Integer id, MultipartFile image, String username);
    boolean isAuthor(String username, Integer adId);
}

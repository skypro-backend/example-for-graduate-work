package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

@Service
public class AdsService {
    public Object getAllAds() {
        return null;
    }

    public Ad addAd(CreateOrUpdateAd properties, MultipartFile file) {
        return null;
    }

    public ExtendedAd getAds(Integer id) {
        return null;
    }

    public void removeAd(Integer id) {
    }

    public Ad updateAds(Integer id, CreateOrUpdateAd newAds) {
        return null;
    }

    public Ads getAdsAllUser() {
        return null;
    }

    public Ad updateImage(Integer id, MultipartFile image) {
        return null;
    }
}

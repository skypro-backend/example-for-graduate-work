package ru.skypro.homework.service;

import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

public interface AdService {
    Ads getAllAds();
    Ad createAd(CreateOrUpdateAd ad, String image, Integer userId);
    ExtendedAd getExtAd(Integer id);
    Ad deleteAd(Integer id);
    Ad pathAd(CreateOrUpdateAd ad, Integer id);
    Ads getAllAdsForUser(Integer userId);
    String pathImageAd(Integer id, String image);
}

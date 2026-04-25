package ru.skypro.homework.service;

import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;

public interface AdService {
    Ads getAllAds();
    ExtendedAd getExtendedAd(Integer id);
    Ad createAd(AdEntity adEntity);
    void deleteAd(Integer id);
    Ad updateAd(Integer id, AdEntity updatedAd);
    Ads getAdsByUser(Integer userId);
    Ad updateImage(Integer id, byte[] image);
}
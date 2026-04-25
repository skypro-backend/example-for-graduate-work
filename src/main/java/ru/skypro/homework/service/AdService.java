package ru.skypro.homework.service;

import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;

public interface AdService {
    Ads getAllAds();

    ExtendedAd getExtendedAd(Integer id);

    Ad createAd(AdEntity adEntity, String email);

    void deleteAd(Integer id, String email);

    Ad updateAd(Integer id, AdEntity updatedAd, String email);

    Ads getAdsByUser(String email);

    Ad updateImage(Integer id, byte[] image);
}
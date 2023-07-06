package ru.skypro.homework.service;

import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.model.Ad;

public interface AdsService {

    Ad createAds(Ad ad);

    void deleteAd(int id);

    Ad[] getAllAds();

    FullAds getAdById(int adId);
}

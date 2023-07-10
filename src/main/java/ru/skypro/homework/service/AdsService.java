package ru.skypro.homework.service;

import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.dto.ResponseWrapperAds;
import ru.skypro.homework.model.Ad;

public interface AdsService {

//    Ad createAds(Ad ad);

    Ad getAdById(int id);

    void deleteAd(int id);

    ResponseWrapperAds getAllAds();


    FullAds getFullAdById(int adId);
}

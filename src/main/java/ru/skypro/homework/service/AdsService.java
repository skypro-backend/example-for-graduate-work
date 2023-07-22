package ru.skypro.homework.service;

import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.dto.ResponseWrapperAds;
import ru.skypro.homework.model.Ad;

public interface AdsService {

//    Ad createAds(Ad ad);

    Ad getAdById(int id);

    void deleteAd(int id);

    ResponseWrapperAds getAllAds();

    void createAds(CreateAds ads);


    AdsDTO updateAd(int id, CreateAds createAds);

    FullAds getFullAdById(int adId);
}

package ru.skypro.homework.service;

import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.model.Ad;

public interface AdsService {


    Ad[] getAllAds();

    FullAds getAdById(int adId);
}

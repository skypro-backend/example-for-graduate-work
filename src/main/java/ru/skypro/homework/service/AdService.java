package ru.skypro.homework.service;

import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.projections.ExtendedAd;

public interface AdService {

    AdsDTO getAllAds();

    AdDTO addAd();

    ExtendedAd getAdFullInfo(int id);

    AdsDTO updateAd();

    void deleteAd();

    AdsDTO getUserAdds();

    String updateImage();


}

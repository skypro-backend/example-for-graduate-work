package ru.skypro.homework.service;

import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.projections.ExtendedAd;

public interface AdService {

    AdsDTO getAllAds();

    AdDTO addAd(CreateOrUpdateAd createAd, String imagePath);

    ExtendedAd getAdFullInfo(int id);

    AdsDTO updateAd(CreateOrUpdateAd updateAd, int id);

    void deleteAd(int id);

    AdsDTO getUserAdds();

    String updateImage();


}

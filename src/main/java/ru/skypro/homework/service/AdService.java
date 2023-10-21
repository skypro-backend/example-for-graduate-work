package ru.skypro.homework.service;

import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.projections.ExtendedAd;

public interface AdService {

    AdsDTO getAllAds();

    AdDTO addAd(CreateOrUpdateAdDTO createOrUpdateAdDTO, String pathImage);

    ExtendedAd getAds(int id);

    AdsDTO updateAd(int id, CreateOrUpdateAdDTO createOrUpdateAdDTO);

    void removeAd( int id);

    AdsDTO getAdsMe();

    String updateImage(int id, String pathImage);


}

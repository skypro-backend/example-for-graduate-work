package ru.skypro.homework.service;

import org.springframework.data.crossstore.ChangeSetPersister;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.projections.Ads;

import ru.skypro.homework.projections.CreateOrUpdateAd;
import ru.skypro.homework.projections.ExtendedAd;

public interface AdService {

    Ads getAllAds();

    AdDTO addAd(CreateOrUpdateAd createOrUpdateAdDTO, String pathImage);

    ExtendedAd getAds(int id) throws ChangeSetPersister.NotFoundException;

    Ads updateAd(int id, CreateOrUpdateAd createOrUpdateAdDTO);

    void removeAd( int id);

    Ads getAdsMe();

    String updateImage(int id, String pathImage);


}

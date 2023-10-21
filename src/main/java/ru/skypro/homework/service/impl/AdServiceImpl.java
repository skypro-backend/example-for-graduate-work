package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.projections.Ads;
import ru.skypro.homework.projections.CreateOrUpdateAd;

import ru.skypro.homework.projections.ExtendedAd;
import ru.skypro.homework.service.AdService;

@Service
public class AdServiceImpl implements AdService {


    @Override
    public Ads getAllAds() {
        return null;
    }

    public AdDTO addAd(CreateOrUpdateAd createOrUpdateAdDTO, String pathImage) {
        return null;
    }


    @Override
    public ExtendedAd getAds(int id) {
        return null;
    }

    public Ads updateAd(int id, CreateOrUpdateAd createOrUpdateAdDTO) {
        return null;
    }

    @Override
    public void removeAd(int id) {

    }

    @Override
    public Ads getAdsMe() {
        return null;
    }

    @Override
    public String updateImage(int id, String pathImage) {
        return null;
    }


}


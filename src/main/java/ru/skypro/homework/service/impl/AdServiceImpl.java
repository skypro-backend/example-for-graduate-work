package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.projections.ExtendedAd;
import ru.skypro.homework.service.AdService;

import static ru.skypro.homework.service.ReturnableStubs.*;

@Service
public class AdServiceImpl implements AdService {


    @Override
    public AdsDTO getAllAds() {
        return null;
    }

    @Override
    public AdDTO addAd(CreateOrUpdateAdDTO createOrUpdateAdDTO, String pathImage) {
        return null;
    }


    @Override
    public ExtendedAd getAds(int id) {
        return null;
    }

    @Override
    public AdsDTO updateAd(int id, CreateOrUpdateAdDTO createOrUpdateAdDTO) {
        return null;
    }

    @Override
    public void removeAd(int id) {

    }

    @Override
    public AdsDTO getAdsMe() {
        return null;
    }

    @Override
    public String updateImage(int id, String pathImage) {
        return null;
    }


}


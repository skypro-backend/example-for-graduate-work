package ru.skypro.homework.service.impl;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.service.AdService;

public class AdServiceImpl implements AdService {
    @Override
    public AdsDTO getAllAds() {
        return null;
    }

    @Override
    public AdDTO addAd(CreateOrUpdateAdDTO createOrUpdateAdDTO, MultipartFile image) {
        return null;
    }

    @Override
    public ExtendedAdDTO getAdInfo(Integer adId) {
        return null;
    }

    @Override
    public Void deleteAd(Integer adId) {
        return null;
    }

    @Override
    public AdDTO patchAd(Integer adId, CreateOrUpdateAdDTO createOrUpdateAdDTO) {
        return null;
    }

    @Override
    public AdsDTO getAllAdsByAuthor() {
        return null;
    }

    @Override
    public Void patchAdImage(Integer adId, MultipartFile image) {
        return null;
    }
}

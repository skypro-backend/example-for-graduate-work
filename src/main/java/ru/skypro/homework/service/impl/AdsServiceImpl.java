package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.AdDto;
import ru.skypro.homework.dto.ads.AdsDto;
import ru.skypro.homework.dto.ads.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ads.ExtendedAdDto;
import ru.skypro.homework.service.AdsService;

import java.util.List;

@Service
public class AdsServiceImpl implements AdsService {

    @Override
    public AdsDto getAllAds() {
        return null;
    }

    @Override
    public AdDto addAd(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile image) {
        return null;
    }

    @Override
    public ExtendedAdDto getAds(Integer id) {
        return null;
    }

    @Override
    public void removeAd(Integer id) {

    }

    @Override
    public AdDto updateAds(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto) {

        return null;
    }

    @Override
    public AdsDto getAdsMe() {
        return null;
    }

    @Override
    public byte[] updateImage(Integer id, MultipartFile image) {
        return null;
    }


}

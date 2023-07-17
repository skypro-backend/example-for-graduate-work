package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;

@Service
public class AdsService {
    public Object getAllAds() {
        return null;
    }

    public AdDto addAd(CreateOrUpdateAdDto properties, MultipartFile file) {
        return null;
    }

    public ExtendedAdDto getAds(Integer id) {
        return null;
    }

    public void removeAd(Integer id) {
    }

    public AdDto updateAds(Integer id, CreateOrUpdateAdDto newAds) {
        return null;
    }

    public AdsDto getAdsAllUser() {
        return null;
    }

    public AdDto updateImage(Integer id, MultipartFile image) {
        return null;
    }
}

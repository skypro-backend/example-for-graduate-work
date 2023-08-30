package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.AdDto;
import ru.skypro.homework.service.AdsService;

import java.util.List;

@Service
public class AdsServiceImpl implements AdsService {

    @Override
    public List<AdDto> getAllAds() {
        return null;
    }

    @Override
    public AdDto addAd(AdDto adDto) {

        return adDto;
    }

    @Override
    public AdDto addAd(AdDto adDto, MultipartFile multipartFile) {

        return adDto;
    }

    @Override
    public AdDto getAd(int adId) {
        return null;
    }

    @Override
    public void deleteAd(int adId) {

    }

    @Override
    public AdDto updateAd(int adId, AdDto adDto) {

        return adDto;
    }

    @Override
    public List<AdDto> getMyAds() {
        return null;
    }

    @Override
    public void updateImage(int adId, MultipartFile multipartFile) {

    }


}

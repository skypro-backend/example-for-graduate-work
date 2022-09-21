package ru.skypro.homework.service;

import ru.skypro.homework.models.dto.AdsDto;
import ru.skypro.homework.models.dto.CreateAdsDto;
import ru.skypro.homework.models.dto.FullAdsDto;
import ru.skypro.homework.models.entity.Ads;
import ru.skypro.homework.models.entity.Images;

import java.util.List;

public interface AdsService {

    List<AdsDto> getALLAds();

    AdsDto addAds(CreateAdsDto ads, Images image);

    List<AdsDto> getAdsMe(Boolean authenticated, String authority, Object credentials, Object details, Object principal);

    void removeAds(Integer id);

    FullAdsDto getFullAds(Integer id);

    AdsDto updateAds(Integer id, CreateAdsDto ads);

    Ads getAds(Integer id);
}

package ru.skypro.homework.service;

import ru.skypro.homework.models.dto.AdsDto;
import ru.skypro.homework.models.dto.CreateAdsDto;
import ru.skypro.homework.models.dto.FullAdsDto;

import java.util.List;

public interface AdsService {

    List<AdsDto> getALLAds();

    CreateAdsDto addAds(CreateAdsDto ads);

    List<AdsDto> getAdsMe(Boolean authenticated, String authority, Object credentials, Object details, Object principal);

    void removeAds(Integer id);

    FullAdsDto getAds(Integer id);

    AdsDto updateAds(Integer id, AdsDto ads);

}

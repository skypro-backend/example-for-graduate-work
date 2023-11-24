package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;

import java.util.List;

@Service
public interface AdService {
    //    AdsDto getAll ();
    CreateOrUpdateAdDto addAds(CreateOrUpdateAdDto createOrUpdateAdDto, Authentication authentication);
    CreateOrUpdateAdDto updateAds(CreateOrUpdateAdDto createOrUpdateAdDto, Authentication authentication, int pk);

    List<AdDto> getAllAds(Authentication authentication);

    ExtendedAdDto getAds(int pk);
    AdsDto getAdsMe(Authentication authentication);
    void removeAd(int pk);

}

package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.dto.ad.AdsDto;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ad.ExtendedAdDto;

public interface AdService {
    AdsDto getAllAds();
    AdDto addAd(String email, CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile image);
    ExtendedAdDto getAd(Integer id);
    void removeAd(String email, Integer id);
    AdDto updateAd(String email, Integer id, CreateOrUpdateAdDto dto);
    AdsDto getAdsMe(String email);
    byte[] updateAdImage(String email, Integer id, MultipartFile image);
}

package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ads.AdDto;
import ru.skypro.homework.dto.Ads.AdsDto;
import ru.skypro.homework.dto.Ads.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.Ads.ExtendedAdDto;

public interface AdService {

    AdsDto getAllAds();

    AdsDto getAdsByCurrentUser(String email);

    ExtendedAdDto getAd(Integer id);

    AdDto createAd(String email, CreateOrUpdateAdDto properties, MultipartFile image);

    AdDto updateAd(String email, Integer id, CreateOrUpdateAdDto updateAd);

    void deleteAd(String email, Integer id);

    byte[] updateAdImage(String email, Integer id, MultipartFile image);
}

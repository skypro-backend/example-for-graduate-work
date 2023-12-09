package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;


public interface AdService {
    AdsDto getAllAds();

    AdDto addAd(MultipartFile file, CreateOrUpdateAdDto dto);

    ExtendedAdDto getAd(Integer id);

    void removeAd(Integer id);

    AdDto updateAd(Integer id, CreateOrUpdateAdDto dto);

    AdsDto getAdsMe();

    byte[] updateAdImage(Integer id, MultipartFile file);

    byte[] getImage(Integer id);

    boolean existByAdIdAndUsername(Integer id, String username);

}

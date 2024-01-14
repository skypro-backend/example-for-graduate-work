package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.AdDto;
import ru.skypro.homework.dto.ads.AdsDto;
import ru.skypro.homework.dto.ads.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ads.ExtendedAdDto;

public interface AdService {

    AdsDto findAll();

    AdDto addAd(MultipartFile image, CreateOrUpdateAdDto properties, String userName);

    ExtendedAdDto getAdById(String userName, int id);

    void deleteAd(int id, String userName);

    AdDto updateAd(int id, CreateOrUpdateAdDto dto, String userName);

    AdsDto getAdsAuthorizedUser(String userName);

    String updateImage(int id, MultipartFile image, String userName);
}

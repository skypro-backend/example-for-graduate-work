package ru.skypro.homework.service;


import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entities.AdEntity;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAd;

public interface AdService {

    AdEntity getAdsById(Integer id);
    AdDto addAd(CreateOrUpdateAd properties, MultipartFile image);
    AdsDto getAllAds();
    void deleteAds(int id);
    AdDto updateAds(Integer id, CreateOrUpdateAd dto);
    AdsDto getAdsMe(String username);
    void updateImage(Integer id, MultipartFile image);


}
package ru.skypro.homework.service.ads;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.AdDto;
import ru.skypro.homework.dto.ads.AdsDto;
import ru.skypro.homework.dto.ads.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ads.ExtendedAdDto;


public interface AdsService {

    AdsDto getAllAds();

    AdDto addAd(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile image);

    ExtendedAdDto getAds(Integer id);

    void removeAd(Integer id);

    AdDto updateAds(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto);

    AdsDto getAdsMe();

    byte[] updateImage(Integer id, MultipartFile image);
}

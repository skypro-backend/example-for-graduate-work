package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;

import java.util.List;

public interface AdsService {

    List<AdDto> getAds();

    AdDto addAd(AdDto adDto);

    AdDto addAd(AdDto adDto, MultipartFile multipartFile);

    AdDto getAd(int adId);

    void deleteAd(int adId);

    AdDto updateAd(int adId, AdDto adDto);

    List<AdDto> getMyAds();

    void updateImage(int adId, MultipartFile multipartFile);
}

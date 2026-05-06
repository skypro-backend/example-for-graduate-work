package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.request.AdRequestDto;
import ru.skypro.homework.dto.response.AdResponseDto;
import ru.skypro.homework.dto.response.AdsResponseDto;
import ru.skypro.homework.dto.response.ExtendedAdResponseDto;

public interface AdService {

    AdsResponseDto getAllAds();

    ExtendedAdResponseDto getAd(Integer id);

    AdsResponseDto getMyAds();

    AdResponseDto addAd(AdRequestDto properties, MultipartFile image);

    AdResponseDto updateAd(Integer id, AdRequestDto dto);

    void deleteAd(Integer id);

    void updateImage(Integer id, MultipartFile image);

    boolean isOwner(Integer adId, Authentication authentication);
}

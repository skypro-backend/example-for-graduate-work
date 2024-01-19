package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

import javax.transaction.Transactional;
import java.io.IOException;


public interface AdsService {
    AdsDto getAllAds();

    AdDto addAd(CreateOrUpdateAd createOrUpdateAdDTO, MultipartFile image, Authentication authentication);

    ExtendedAd getAd(long id);

    void deleteAd(long id, Authentication authentication);

    AdDto updateAd(long id, CreateOrUpdateAd createOrUpdateAd, Authentication authentication);

    AdsDto getAdsMe(Authentication authentication);
    @Transactional
    void updateAdImage(Long id, MultipartFile image, Authentication authentication);
}

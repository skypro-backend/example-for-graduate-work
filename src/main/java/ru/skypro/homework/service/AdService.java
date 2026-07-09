package ru.skypro.homework.service;

import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;


public interface AdService {

    Ad createdAd(CreateOrUpdateAd dto, MultipartFile image, Authentication authentication);

    Ads getAllAds();

    Ads getAdsMe(Authentication authentication);

    ExtendedAd getAdById(Long id);

    Ad updateAd(Long id, CreateOrUpdateAd dto, Authentication authentication);

    void deleteAd(Long id, Authentication authentication);

    void updateAdImage(Long id, MultipartFile image, Authentication authentication);

    Resource updateAdImageAndReturn(Long id, MultipartFile image, Authentication authentication);


}

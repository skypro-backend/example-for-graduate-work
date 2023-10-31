package ru.skypro.homework.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.AdDTO;
import ru.skypro.homework.dto.ads.Ads;
import ru.skypro.homework.dto.ads.CreateOrUpdateAd;
import ru.skypro.homework.dto.ads.ExtendedAd;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.ImageAd;


public interface AdsService {
    Ads getAllAds();
    AdDTO addAd(CreateOrUpdateAd createAd, MultipartFile image, Authentication authentication);

    ExtendedAd getAds(int id, Authentication authentication);

//    @PreAuthorize("principal.admin or #username == authentication.principal.username")
    void removeAd (int id, Authentication authentication);

//    @PreAuthorize("principal.admin or #username == authentication.principal.username")
    AdDTO updateAds(int id, CreateOrUpdateAd updateAd, Authentication authentication);

    Ads getAdsMe(Authentication authentication);

//    @PreAuthorize("principal.admin or #username == authentication.principal.username")
    ImageAd updateImage(int id, MultipartFile image, Authentication authentication);

    byte [] getImage (String id);

}
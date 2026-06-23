package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

public interface AdService {
    Ads getAllAds();
    Ad addAd(CreateOrUpdateAd properties, MultipartFile image, String username);
    ExtendedAd getAd(Integer id);
    void removeAd(Integer id);
    Ad updateAd(Integer id, CreateOrUpdateAd dto);
    Ads getAdsMe(String username);
    void updateAdImage(Integer id, MultipartFile image);
    boolean isAuthorOrAdmin(Integer id, String username);
}
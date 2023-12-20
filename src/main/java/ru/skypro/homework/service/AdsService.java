package ru.skypro.homework.service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

public interface AdsService {
    Ads getAllAds();
    Ad addAd (CreateOrUpdateAd properties, MultipartFile image);
    ExtendedAd getAds(Integer adId);
    void removeAd(Integer adId);
    CreateOrUpdateAd updateAd(Integer adId, CreateOrUpdateAd properties);
    Ads getAdsMe();
    void updateImage(Integer adId, MultipartFile image);

}

package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendAd;


public interface AdsService {

    Ads getAllAds();
    Ad addAd(MultipartFile file, CreateOrUpdateAd dto);
    ExtendAd getAd(Integer id);
    void removeAd(Integer id);
    Ad updateAd(Integer id, CreateOrUpdateAd dto);
    Ads getAdsMe();
    byte[] updateAdImage(Integer id, MultipartFile file);
    byte[] getImage(Integer id);
    boolean existByAdIdAndUsername(Integer id, String username);


}

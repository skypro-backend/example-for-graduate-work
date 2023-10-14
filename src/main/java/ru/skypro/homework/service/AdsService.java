package ru.skypro.homework.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

public interface AdsService {

    Ads getAllAds();

    Ad addAd(CreateOrUpdateAd properties, MultipartFile image);

    ExtendedAd getAds(Integer id);

    boolean removeAd(Integer id);

    Ad updateAds(Integer id, CreateOrUpdateAd createOrUpdateAd);

    Ads getAdsMe();

    String updateImage(Integer id, MultipartFile file);

}

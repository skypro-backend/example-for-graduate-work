package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.exceptions.AdNotFoundException;

public interface AdService {

    Ad postAd(CreateOrUpdateAd properties, MultipartFile file, String username);

    Ads getAllAds();

    ExtendedAd getAdById(int id) throws AdNotFoundException;

    void deleteAdById(int id);

    Ad patchAdById(int id, CreateOrUpdateAd createOrUpdateAd) throws AdNotFoundException;

    Ads getMyAds(String userName);

    Byte[] patchAdsImageById(int id, MultipartFile file);

}

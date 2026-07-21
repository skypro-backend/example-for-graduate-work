package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

public interface AdService {

    Ads getAllAds();

    Ad createAd(CreateOrUpdateAd ad, MultipartFile image);

    ExtendedAd getAd(Integer id);

    void deleteAd(Integer id);

    Ad updateAd(Integer id, CreateOrUpdateAd ad);

    Ads getMyAds();

    void updateImage(Integer id, MultipartFile image);
}
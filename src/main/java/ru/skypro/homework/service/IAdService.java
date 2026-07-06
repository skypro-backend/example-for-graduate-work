package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.ad.Ad;
import ru.skypro.homework.ad.Ads;
import ru.skypro.homework.ad.CreateOrUpdateAd;
import ru.skypro.homework.ad.ExtendedAd;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface IAdService {
    Ads getAllAds(int offset, int limit);
    Optional<ExtendedAd> getAdById(long id);
    Ads getMyAds(Authentication auth);
    Ad createAd(CreateOrUpdateAd data, Authentication auth);
    Ad updateAd(long id, CreateOrUpdateAd data, Authentication auth);
    void deleteAd(long id, Authentication auth);
    void updateImage(long id, MultipartFile image, Authentication auth);
}
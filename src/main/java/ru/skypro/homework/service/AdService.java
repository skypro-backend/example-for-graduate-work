package ru.skypro.homework.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

import java.io.IOException;

public interface AdService {

    Ad addAd(MultipartFile image, CreateOrUpdateAd adDetails, UserDetails userDetails);
    Ads getAllAds();
    Ads getAdsByCurrentUser(UserDetails userDetails);
    ExtendedAd getFullAd(int id);
    Ad updateAd(int id, CreateOrUpdateAd adDetails, UserDetails userDetails);
    void removeAd(int id, UserDetails userDetails);
    void updateImage(int id, MultipartFile image, UserDetails userDetails);
}

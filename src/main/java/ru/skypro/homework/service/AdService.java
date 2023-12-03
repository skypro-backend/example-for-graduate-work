package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

public interface AdService {

    Ad addAd(MultipartFile image, CreateOrUpdateAd adDetails, UserDetails userDetails);

    Ads getAllAds();

    Ads getAdsByCurrentUser(UserDetails userDetails);

    ExtendedAd getFullAd(Integer id);

    Ad updateAd(Integer id, CreateOrUpdateAd adDetails);

    ResponseEntity<String> removeAd(Integer id);
}

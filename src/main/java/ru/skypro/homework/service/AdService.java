package ru.skypro.homework.service;


import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

import java.io.IOException;
import java.util.Collection;

public interface AdService {
   AdsDto getAllAds();

    AdDto addAd(CreateOrUpdateAdDto ad, MultipartFile imageBytes, Authentication authentication) throws IOException;
    ExtendedAdDto getAds (int id);
    void removeAd (int id, Authentication authentication);
    AdDto updateAds(int id, CreateOrUpdateAdDto adDto);
    void updateImage(Integer id, MultipartFile image, Authentication authentication);
    AdsDto getMyAds(Authentication authentication);
 ExtendedAdDto findExtendedAd(Integer id);
}
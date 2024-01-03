package ru.skypro.homework.service;


import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

import java.io.IOException;
import java.util.Collection;

public interface AdService {
    AdsDto getAll();
    AdDto createAd(CreateOrUpdateAdDto ad, MultipartFile imageBytes, Authentication authentication) throws IOException;
    ExtendedAdDto getAdById(int id);
    void deleteAd(int id);
    AdDto updateAd(int id, CreateOrUpdateAdDto createOrUpdateAdDto);
    AdsDto getAdsForCurrentUser();
    void updateAdImage(Integer id, MultipartFile image, Authentication authentication);
    AdsDto getMyAds(Authentication authentication);
    AdsDto getMyAds();
}
package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

import java.io.IOException;
import java.util.List;

public interface AdsService {


    AdsDto create(MultipartFile imageFiles, CreateOrUpdateAd createOrUpdateAd, Authentication authentication) throws IOException;

    AdsDto update(Integer id, CreateOrUpdateAd createOrUpdateAd);

    void updateAdsImage(Integer id, MultipartFile imageFile) throws IOException;

    List<AdsDto> get(Authentication authentication);

    ExtendedAd getAdsById(Integer id);

    List<AdsDto> getAllAds();

    void remove(Integer id);
}

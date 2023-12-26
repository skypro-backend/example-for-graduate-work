package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;

import java.nio.file.AccessDeniedException;

public interface AdService {

    AdsDTO getAllAds();

    AdDTO addAd(CreateOrUpdateAdDTO createOrUpdateAdDTO, MultipartFile image, Authentication authentication);

    ExtendedAdDTO getAd(Long id);

    void deleteAd(Long id, Authentication authentication) throws AccessDeniedException;

    AdDTO updateAd(Long id, CreateOrUpdateAdDTO createOrUpdateAdDTO, Authentication authentication) throws AccessDeniedException;

    AdsDTO getAdsMe(Authentication authentication);

    void updateAdImage(Long id, MultipartFile image, Authentication authentication) throws AccessDeniedException;


}

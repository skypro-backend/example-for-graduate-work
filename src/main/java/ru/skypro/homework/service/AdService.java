package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.entity.AdEntity;

import java.io.IOException;
import java.security.Principal;
import java.util.Collection;

public interface AdService {

    Collection<AdDTO> getAllAds(String title);
    AdDTO createAd(CreateOrUpdateAdDTO createAdDTO, MultipartFile image, Authentication authentication);
    ExtendedAdDTO getFullAd(Long adId);
    void deleteAd(Long adId);
    AdDTO updateAd(CreateOrUpdateAdDTO createAdDTO, Long adId);
    Collection<AdDTO> getUserAllAds();

    String updatePhoto(Long adId,MultipartFile image) throws IOException;

    byte[] getAdImage(Long adId);
}

package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.entity.AdEntity;

import java.io.IOException;
import java.security.Principal;

public interface AdService {

    AdEntity findById(Integer id);

    AdsDTO getMyAds (Principal principal);
    AdsDTO getAllAds();
    ExtendedAdDTO getInfoAboutAd(Integer adId);
    Boolean deleteAd(Integer adId);

    byte[] updateImageInAd(MultipartFile image, Integer adId) throws IOException;
}

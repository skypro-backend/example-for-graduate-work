package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;

import java.io.IOException;

public interface AdService {

    Ads findAllAds();
    AdDTO createAd(CreateOrUpdateAd createOrUpdateAd,
                   Authentication authentication,
                   MultipartFile multipartFile) throws IOException;
    ExtendedAd getAd(int id);
    void deleteAd(int id);
    Ads findAllUserAds(Authentication authentication);
    AdDTO updateAd(int id,
                   CreateOrUpdateAd createOrUpdateAd);
    String editAdImage(int id,
                         MultipartFile multipartFile) throws IOException;

}

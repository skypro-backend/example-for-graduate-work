package ru.skypro.homework.service.ads;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.projection.AdView;
import ru.skypro.homework.projection.Ads;
import ru.skypro.homework.projection.CreateOrUpdateAd;
import ru.skypro.homework.projection.ExtendedAd;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public interface AdsService {

    Ads getAllAds();
    AdDTO createAd(CreateOrUpdateAd properties, MultipartFile image, Authentication authentication);
    ExtendedAd getAdFullInfo(Integer id);
    void deleteAd(Integer id);
    AdView updateAd(Integer id, CreateOrUpdateAd properties);
    Ads getAllAdsByUser(Authentication authentication);
    String updateImage(Integer id, MultipartFile image);

}

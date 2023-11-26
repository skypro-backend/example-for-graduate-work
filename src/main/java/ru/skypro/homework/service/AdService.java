package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.PhotoEntity;

import java.io.IOException;

public interface AdService {

    Ads getAllAds();

    Ad addAd(CreateOrUpdateAd properties, MultipartFile image) throws IOException;

    ExtendedAd getAds(Integer id);

    boolean removeAd(Integer id);

    Ad updateAds(Integer id, CreateOrUpdateAd dto);

    Ads getAdsMe(String username);

    void updateImage(Integer id, MultipartFile image) throws IOException; // todo возвращаемое значение, как вариант

    PhotoEntity findPhoto(Integer id);
}

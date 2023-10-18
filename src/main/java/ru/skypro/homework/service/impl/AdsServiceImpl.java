package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.AdDTO;
import ru.skypro.homework.dto.ads.Ads;
import ru.skypro.homework.dto.ads.CreateOrUpdateAd;
import ru.skypro.homework.dto.ads.ExtendedAd;
import ru.skypro.homework.service.AdsService;

import java.util.List;

@Service
public class AdsServiceImpl implements AdsService {
    @Override
    public Ads getAllAds() {
        AdDTO dto = new AdDTO();
        dto.setPk(1);
        dto.setAuthor(1);
        dto.setPrice(10000);
        dto.setTitle("Заголовок");
        return new Ads(List.of(dto));
    }

    @Override
    public AdDTO addAd(CreateOrUpdateAd createAd, MultipartFile image) {
        AdDTO dto = new AdDTO();
        dto.setPk(1);
        dto.setAuthor(1);
        dto.setPrice(20000);
        dto.setTitle("Что-то новое");
        return dto;
    }

    @Override
    public ExtendedAd getAds(int id) {
        ExtendedAd extendedAd = new ExtendedAd();
        extendedAd.setPk(1);
        extendedAd.setAuthorFirstName("Ivan");
        extendedAd.setAuthorLastName("Ivanov");
        extendedAd.setTitle("Заголовок");
        extendedAd.setPrice(15000);
        return extendedAd;
    }

    @Override
    public void removeAd(int id) {

    }

    @Override
    public AdDTO updateAds(int id, CreateOrUpdateAd updateAd) {
        AdDTO dto = new AdDTO();
        dto.setPk(1);
        dto.setAuthor(1);
        dto.setPrice(20000);
        dto.setTitle("Заголовок");
        return dto;
    }

    @Override
    public Ads getAdsMe() {
        AdDTO dto = new AdDTO();
        dto.setPk(1);
        dto.setAuthor(1);
        dto.setPrice(10000);
        dto.setTitle("Заголовок");
        return new Ads(List.of(dto));
    }

    @Override
    public String updateImage(int id, MultipartFile image) {
        return null;
    }
}
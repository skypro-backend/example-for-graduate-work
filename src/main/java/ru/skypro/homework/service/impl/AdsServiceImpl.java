package ru.skypro.homework.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendAd;
import ru.skypro.homework.service.AdsService;

@Service
public class AdsServiceImpl implements AdsService  {

    @Override
    public Ads getAllAds() {
        return null;
    }

    @Override
    public Ad addAd(MultipartFile file, CreateOrUpdateAd dto) {
        return null;
    }

    @Override
    public ExtendAd getAd(Integer id) {
        return null;
    }

    @Override
    public void removeAd(Integer id ) {

    }

    @Override
    public Ad updateAd(Integer id, CreateOrUpdateAd dto) {
        return null;
    }

    @Override
    public Ads getAdsMe() {
        return null;
    }

    @Override
    public byte[] updateAdImage(Integer id, MultipartFile file) {
        return new byte[0];
    }

    @Override
    public byte[] getImage(Integer id) {
        return new byte[0];
    }

    @Override
    public boolean existByAdIdAndUsername(Integer id, String username) {
        return false;
    }
}




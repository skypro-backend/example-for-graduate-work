package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdsService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final AdRepository adRepository;

    @Override
    public Ads getAllAds() {
        List<AdEntity> adEntityList = adRepository.findAll();
        List<Ad> adList = AdsMapper.INSTANCE.adEntityListToAdList(adEntityList);
        Integer sizeList = adList.size();

        Ads ads = new Ads();
        ads.setCount(sizeList);
        ads.setResults(adList);
        return ads;
    }

    @Override
    public Ad addAd(CreateOrUpdateAd properties, MultipartFile image) {
        return null;
    }

    @Override
    public ExtendedAd getAds(Integer id) {
        return null;
    }

    @Override
    public boolean removeAd(Integer id) {
        return false;
    }

    @Override
    public Ad updateAds(Integer id, CreateOrUpdateAd createOrUpdateAd) {
        return null;
    }

    @Override
    public Ads getAdsMe() {
        return null;
    }

    @Override
    public String updateImage(Integer id, MultipartFile file) {
        return null;
    }

}

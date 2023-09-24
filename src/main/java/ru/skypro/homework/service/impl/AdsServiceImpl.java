package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdsService;

import java.util.List;

@Service
public class AdsServiceImpl implements AdsService {
    private AdRepository adRepository;

    public AdsServiceImpl(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @Override
    public AdsDto getAllAds() {
        List<Ad> adList = adRepository.findAll();
        return new AdsDto().fromAdsList(adList);
    }

    @Override
    public Ad getAdsById(Integer id) {
        return null;
    }

    @Override
    public AdDto createAds(CreateOrUpdateAdDto createOrUpdateAdDto) {
        return null;
    }

    @Override
    public AdDto getAdById(Integer id) {
        return null;
    }

    @Override
    public void removeAd(Integer id) {

    }

    @Override
    public AdDto updateAdById(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto) {
        return null;
    }

    @Override
    public AdsDto getAllAdsForUser(String userName) {
        return null;
    }
}

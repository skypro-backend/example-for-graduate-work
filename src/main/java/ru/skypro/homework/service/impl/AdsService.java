package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
<<<<<<< HEAD
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

@Service
public class AdsService {
=======
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.repository.AdsRepository;

@Service
public class AdsService {

    private final AdsRepository adsRepository;

    public AdsService(AdsRepository adsRepository) {
        this.adsRepository = adsRepository;
    }
>>>>>>> origin/vik_branch_rep
    public Object getAllAds() {
        return null;
    }

<<<<<<< HEAD
    public Ad addAd(CreateOrUpdateAd properties, MultipartFile file) {
        return null;
    }

    public ExtendedAd getAds(Integer id) {
=======
    public AdDto addAd(CreateOrUpdateAdDto properties, MultipartFile file) {
        return null;
    }

    public ExtendedAdDto getAds(Integer id) {
>>>>>>> origin/vik_branch_rep
        return null;
    }

    public void removeAd(Integer id) {
    }

<<<<<<< HEAD
    public Ad updateAds(Integer id, CreateOrUpdateAd newAds) {
        return null;
    }

    public Ads getAdsAllUser() {
        return null;
    }

    public Ad updateImage(Integer id, MultipartFile image) {
=======
    public AdDto updateAds(Integer id, CreateOrUpdateAdDto newAds) {
        return null;
    }

    public AdsDto getAdsAllUser() {
        return null;
    }

    public AdDto updateImage(Integer id, MultipartFile image) {
>>>>>>> origin/vik_branch_rep
        return null;
    }
}

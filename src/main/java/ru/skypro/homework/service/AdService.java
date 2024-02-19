package ru.skypro.homework.service;

import ru.skypro.homework.model.Ad;

import java.util.List;

public interface AdService {
    List<Ad> getAllAds();

    Ad getAdById(Long id);

    Ad createAd(Ad ad);

    Ad updateAd(Long id, Ad ad);

    void deleteAd(Long id);
}

package ru.skypro.homework.service;

import ru.skypro.homework.model.Ad;

import java.util.List;
import java.util.Optional;

public interface AdService {
    List<Ad> getAllAds();

    Optional<Ad> getAdById(Long id);

    Ad createAd(Ad ad);

    Ad updateAd(Long id, Ad ad);

    void deleteAd(Long id);
}

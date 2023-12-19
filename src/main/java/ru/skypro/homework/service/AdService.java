package ru.skypro.homework.service;

import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AdService {
    List<Ad> findAll();
    Ad findById(Long id);
    List<Ad> getAdByAuthUser();
    AdDTO addAd(CreateOrUpdateAdDTO ad, byte[] img);
    Ad updateAd(CreateOrUpdateAdDTO ad);
    Image updateImage(byte[] img);
    void deleteAd(Ad ad);
}

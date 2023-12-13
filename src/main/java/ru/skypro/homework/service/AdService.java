package ru.skypro.homework.service;

import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.model.Ad;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AdService {
    List<Ad> findAll();
    Ad findById(Long id);
    Ad getAdByAuthUser();
    void addAd(CreateOrUpdateAdDTO ad, byte[] img);
    void updateAd(CreateOrUpdateAdDTO ad);
    void updateImage(byte[] img);
    void deleteAd(Ad ad);
}
